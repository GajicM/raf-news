<template>
    <div>
        <h1>Leave a Comment: </h1>
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group id="input-group-1" label="Your Name:" label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="form.username"
            placeholder="Enter name"
            required
          ></b-form-input>
        </b-form-group>
      
        <b-form-group id="input-group-2" label="Your Comment:" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="form.comment"
            placeholder="Enter your comment"
            required
          ></b-form-input>
        </b-form-group>
      
  
       
  
        <b-button type="submit" variant="primary">Submit</b-button>
        <b-button type="reset" variant="danger">Reset</b-button>
      </b-form>
      <b-card class="mt-3" header="Form Data Result">
        <pre class="m-0">{{ form }}</pre>
      </b-card>
    </div>
  </template>
  
  <script>
  import { mapActions,mapMutations,mapState} from 'vuex';
    export default {
    data() {
      return {
        form: {
       
          username: '',
          comment: "",
          newsID:this.post.id,
          timeCreated:null
        },
        show: true
      }
    },
    props:{
        post:null
    },
    methods: {
        ...mapActions([
        'fetchAddComment'
      ]),
      onSubmit(event) {
        event.preventDefault()
        this.form.timeCreated=new Date();
       console.log(this.form)
       console.log("ovde")
        
        this.fetchAddComment(this.form).then(()=>{
            alert("comment added succesfully")
        })
      },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
       this.form.username = ''
        this.form.comment=''
        // Trick to reset/clear native browser form validation state
     
      }
    }
  }
  </script>