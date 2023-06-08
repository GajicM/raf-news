<template>
    <div>
   
        <h2>ID: {{ form.id }}</h2>
      <h2>
        Name: {{  form.firstName +" " +form.lastName  }}
      </h2>
      <h2>
        Email: {{  form.email  }}
      </h2>
      <h2>
        UserType: {{  form.userType  }}
      </h2>
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        id="input-group-1"
        label="firstName"
        label-for="input-1"
        description="First Name"
      >
        <b-form-input
          id="input-1"
          v-model="formC.firstName"
          type="text"
          placeholder="Enter First Name"
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group
        id="input-group-1"
        label="lastName"
        label-for="input-1"
        description="Last Name"
      >
        <b-form-input
          id="input-1"
          v-model="formC.lastName"
          type="text"
          placeholder="Last Name"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="email:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="formC.email"
          placeholder="Enter email"
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-2" label="userType:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="formC.userType"
          placeholder="Enter userType"
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
    name: 'User',
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
          firstName: this.form.firstName,
          lastName:this.form.lastName,
          userType:this.form.userType,
          email:this.form.email,
          userStatus:this.form.userStatus
        },
      
        show: true
      }
    },
    methods: {
        ...mapActions([
        'fetchChangeUser'
      ]),
      onSubmit(event) {
        event.preventDefault()
        this.fetchChangeUser(this.formC).then((res)=>{
          if(res.message)
          alert(res.message)
          else
            alert("User Changed");
           
        })
      },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.formC.firstName = this.form.firstName
        this.formC.lastName = this.form.lastName
        this.formC.userType=this.form.userType,
        this.formC.email=this.form.email,
        this.formC.userStatus=this.form.userStatus
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    },
    watch:{
      form(newVal, oldVal){
        this.formC.firstName = newVal.firstName
        this.formC.lastName = newVal.lastName
        this.formC.userType=newVal.userType,
        this.formC.email=newVal.email,
        this.formC.userStatus=newVal.userStatus


        this.formC.id=this.form.id;
         
        }

    }
  }
  </script>
  
  <style scoped>
  
  </style>
  