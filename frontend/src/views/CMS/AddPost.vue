<template>
    <div>
       
    
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        id="input-group-1"
        label="title"
        label-for="input-1"
        description="Title"
      >
        <b-form-input
          id="input-1"
          v-model="formC.title"
          type="text"
          placeholder="Enter Title"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Text:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="formC.text"
          placeholder="Enter text"
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-3" label="Category:" label-for="input-3">
        <b-form-select
          id="input-3"
          v-model="formC.category"
          :options="getCategoryNames(categories)"
          required
        ></b-form-select>
      </b-form-group>
      <b-form-group id="input-group-2" label="Tags:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="tagsNames"
          placeholder="Enter text"
          required
        ></b-form-input>
      </b-form-group>
      <b-button type="submit" variant="primary">Promeni</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ formC }}</pre>
    </b-card>
    </div>



  </template>
  
  <script>
  import { decodedMap } from '@jridgewell/trace-mapping';
import { mapActions,mapState } from 'vuex'
  export default {
    name: 'AddPost',
    filters: {
      tagsName(value) {
        console.log(value)
        let retVal=[];
            value.forEach(element => {
                retVal.push(element.tag);
            });
            return retVal.toString();
      }
    },
    data() {
      return {
        formC: {
            
          title: '',
          text:'',
          category:'',
          tags:[],
          author:Object,
          date:new Date()
        },
    
        show: true,
        tagsNames:''
      }
    },
    computed: {
      ...mapState([
        'tags',
        'categories',
        'decoded'
      ])
    } ,
    methods: {
        
        ...mapActions([
        'fetchAddNews',
        'fetchChangeTagsInNews',
        'fetchCategories'
            
      ]),
      onSubmit(event) {
        event.preventDefault()
        let category=null;
        this.categories.forEach(el=>{
            if(el.name==this.formC.category)
            category=el;
        })
        let cate=this.formC.category;
        this.formC.category=category;
        this.formC.author={email:this.decoded.sub,id:this.decoded.id}
       console.log(this.tagsNames)
        let tagsArray=this.tagsNames.split(",");
        
       console.log(tagsArray)
       console.log("OVDE")
        this.fetchAddNews(this.formC).then((res)=>{
            let xd={news:res,
            tags:tagsArray
        }
        console.log(xd)
          this.fetchChangeTagsInNews(xd).then(res=>{
            console.log(res);
          
           this.formC.category=cate;
            alert("News Added");
            this.$router.push({name:'cmsNews'})
          })
           
           
        })
      },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.formC.text = ''
        this.formC.title = ''
       this. tags='',
       
          this.date=new Date()
     
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      },
      getCategoryNames(val){
            let retVal=[];
            val.forEach(el=>{
        retVal.push(el.name)})
        return retVal;
        },
        getTagsName(value) {
        console.log(value)
        let retVal=[];
            value.forEach(element => {
                retVal.push(element.tag);
            });
            return retVal.toString();
      }
      
    },mounted(){
           this.tagsNames= this.getTagsName(this.formC.tags)
    }
    
  }
  
  </script>
  
  <style scoped>
  
  </style>
  