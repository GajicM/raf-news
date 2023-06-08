<template>
    <div>
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
    name: 'AddCategory',
    data() {
      return {
        formC: {
          name: '',
          description:''
        },
      
        show: true
      }
    },
    methods: {
        ...mapActions([
        'fetchAddCategory'
      ]),
      onSubmit(event) {
        event.preventDefault()
        this.fetchAddCategory(this.formC).then((res)=>{
          if(res.message)
            alert(res.message)
            else{
            alert("Category Added");
            this.$router.push({ name: 'cmsCategories' })};
        })
      },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.formC.description =''
        this.formC.name = ''
     
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
  }
  </script>
  
  <style scoped>
  
  </style>
  