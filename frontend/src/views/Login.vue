<template>
  <div id="app">
    <Header subtitle="Log In"/>

    <b-form @submit="onSubmit">
      <b-form-group label="Email:" label-for="name">
        <b-form-input id="name" v-model="form.email" type="text" :state="validation"   placeholder="Enter email" required></b-form-input>
      <b-form-invalid-feedback :state="validation">
        Invalid Email Form
      </b-form-invalid-feedback>
      <b-form-valid-feedback :state="validation">
        Looks Good.
      </b-form-valid-feedback>
      </b-form-group>

      <b-form-group label="Password:" label-for="password">
        <b-form-input id="password" v-model="form.password" type="password" required></b-form-input>
        <b-form-invalid-feedback :state="validationP">
       Password must have at least 4 chars, must not contains special chars.
        </b-form-invalid-feedback>
       <b-form-valid-feedback :state="validationP">
        Looks Good.
      </b-form-valid-feedback>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
    </b-form>
  </div>
</template>

<script>

  import { mapActions} from 'vuex';

  export default {
    name: 'LogIn',
    
    components: {
    },

    data() {
      return {
        form: {
          email: '',
          password: ''
        }
      }
    },
  

    

    computed:{
      validation() {
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.form.email))
   {
    return (true)
   }
    
    return (false)
      },
      validationP(){
        let regex=/[.,?@$#%^&@()+_!*|":}{; ]/
        return this.form.password.length>4 && !regex.test(this.form.password)
      },
     
    },




    methods: {
      ...mapActions([
        'login',
       
      ]),

      async onSubmit(e) {
     
        e.preventDefault();
        if(this.validation && this.validationP){
      this.login(this.form).then(res=>{
        if(res.message)
          alert(res.message)
        else
        this.$router.push({ name: 'home' });
      });

        }else alert('Invalid email/password');
      }
    }
  }
</script>

<style scoped>

</style>
