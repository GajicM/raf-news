<template>
    <div class="user">
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
        id="input-group-2"
        label="lastName"
        label-for="input-2"
        description="Last Name"
      >
        <b-form-input
          id="input-2"
          v-model="formC.lastName"
          type="text"
          placeholder="Last Name"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="email:" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="formC.email"
          type="email"
          placeholder="Enter email"
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-4" label="userType:" label-for="input-4">
        <b-form-input
          id="input-4"
          v-model="formC.userType"
          placeholder="Enter userType"
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-5" label="password:" label-for="input-5">
        <b-form-input
          id="input-5"
          v-model="formC.password"
          type="password"
          placeholder="Enter password"
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-6" label="Confirm password:" label-for="input-6">
        <b-form-input
          id="input-6"
          v-model="confirmPassword"
          type="password"
          placeholder="confirm password"
          required
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Dodaj</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ formC }}</pre>
    </b-card>
    </div>

       

  </template>
  
  <script>
  import { mapActions,mapMutations,mapState } from "vuex";
  import { SHA256 } from 'crypto-js';
  export default {
    name:'AddUser',
    data() {
      return {
        formC: {
        
          firstName: '',
          lastName:'',
          userType:'',
          email:'',
          userStatus:'',
          password:'',
          
        },
        confirmPassword:'',
        show: true
      }
    },
    methods: {
        ...mapActions([
        'fetchAddUser'
      ]),
      onSubmit(event) {
        
        event.preventDefault()
        if(this.formC.password===this.confirmPassword){
         this.formC.password= SHA256(this.confirmPassword).toString();
        this.fetchAddUser(this.formC).then((res)=>{
          this.formC.password=this.confirmPassword;
          if(res.message)
          alert(res.message)
          else{
          alert("User added");
           this.$router.push({name:'cmsUser'});}
        })}
        else
        alert("passwords dont match")
      },
      onReset(event) {
     
        event.preventDefault()
        // Reset our form values
        this.formC.firstName = ''
        this.formC.lastName = ''
        this.formC.userType=''
        this.formC.email=''
        this.formC.userStatus=''
        this.formC.password=''
        this.formC.confirmPassword=''
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
  }
  
  </script>
  