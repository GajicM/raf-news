//forma sa naslovom vesti, kategorijom kojoj vest pripada (odabir jedne od postojećih), tekstom i listom tagova. 
<template>
    <div>
        <h5>ID: {{ form.id }}</h5>
      <h5>
        Name: {{ form.title }}
      </h5>
  
      <h5>Opis:</h5>
      <p>
        {{ form.text }} <br><br>
      </p>
      <h5>Kateogrija:</h5>
      <p>
        {{ form.category.name }} <br><br>
      </p>
      <h5>Tagovi:</h5>
      <p>
        {{ form.tags | tagsName }} <br><br>
      </p>
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        id="input-group-1"
        label="name"
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
          placeholder="Enter tags"
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
  import { mapActions, mapState } from 'vuex'
  export default {
    name: 'Post',
    props: {
      form: {
        type: Object,
        required: true,
      },
      categories:[]

      
    },
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
            id:this.form.id,
          title: this.form.title,
          text:this.form.text,
          category:this.form.category.name,
          tags:this.form.tags,
          author:this.form.author,
          visits:this.form.visits,
          date:this.form.date
        },
      
        show: true,
        tagsNames:[]
      }
    },
    computed: {
      ...mapState([
        'tags',
      ])
    } ,
    methods: {
        
        ...mapActions([
        'fetchChangeNews',
        'fetchChangeTagsInNews'
            
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
    
        console.log("ovde")
        console.log(this.tagsNames)
        let tagsArray=this.tagsNames.split(",");
        let xd={news:this.formC,
            tags:tagsArray
        }
        this.fetchChangeNews(this.formC).then((res)=>{
          this.fetchChangeTagsInNews(xd).then(res=>{
            console.log(res);
            this.form.tags = res.tags;
           this.formC.category=cate;
            alert("News Changed");
          
          })
           
           
        })
      },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.formC.description = this.form.description
        this.formC.name = this.form.name
     
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
      
    },   watch:{
      form(newVal, oldVal){
        this.formC.title = newVal.title
        this.formC.text = newVal.text
        this.formC.category=newVal.category.name
        this.formC.tags=newVal.tags;
        this.tagsNames= this.getTagsName(this.formC.tags)
        this.formC.id=newVal.id,
         this.formC.author=newVal.author,
         this.formC.visits=newVal.visits,
         this.formC.date=newVal.date
        }

    },
    
    
    mounted(){
           this.tagsNames= this.getTagsName(this.formC.tags)
    },
 
    
  }
  </script>
  
  <style scoped>
  
  </style>
  