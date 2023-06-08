<template>
    <div>
        <h2>ID: {{ form.id }}</h2>
      <h2>
        Name: {{ form.name }}
      </h2>
  
      <h5>Opis:</h5>
      <p>
        {{ form.description }} <br><br>
      </p>

      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        id="input-group-1"
        label="name"
        label-for="input-1"
        description="Category Name"
      >
        <b-form-input
          id="input-1"
          v-model="formC.name"
          type="text"
          placeholder="Enter Category name"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Description:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="formC.description"
          placeholder="Enter description"
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
  import { mapActions } from 'vuex'
  export default {
    name: 'Category',
    props: {
      form: {
        type: Object,
        required: true,
      }
    },
   
    data() {
      return {
        formC: {
          id:this.form.id,
          name: this.form.name,
          description:this.form.description
        },
      
        show: true
      }
    },
    methods: {
        ...mapActions([
        'fetchChangeCategory'
      ]),
      onSubmit(event) {
        event.preventDefault()
        this.fetchChangeCategory(this.formC).then((res)=>{
          if(res.message)
          alert(res.message)
          else
            alert("Category Changed");
           
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
      }
    },   watch:{
      form(newVal, oldVal){
        this.formC.id = newVal.id
        this.formC.name = newVal.name
        this.formC.description=newVal.description
       

        }

    }
  }
  </script>
  
  <style scoped>
  
  </style>
  