
<template>

 <div id="app">
    <div>
          <b-navbar toggleable="sm" type="dark" variant="info">
          
          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
          <b-navbar-nav>
            <b-nav-item to="/">Home</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav>
            <b-nav-item to="/mostRead">Najcitanije</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav>
            <b-nav-item-dropdown  text="Kategorije" right  >
              <b-col  v-for="cat in categories" :key="cat.id">
                <b-dropdown-item :to="`/news/getNewsByCategory/${cat.id}`">
                    {{ cat.name }}
                </b-dropdown-item> </b-col>
                  
            </b-nav-item-dropdown>
          </b-navbar-nav>
          <b-navbar-nav>
            <b-nav-item to="/about">About</b-nav-item>
          </b-navbar-nav>
            <b-navbar-nav class="ml-auto">
              <b-nav-item v-if="decoded" disabled>{{ decoded.firstName }}</b-nav-item>
              <b-nav-item v-if="jwt" to="/cms/categories">Kategorije</b-nav-item>
              <b-nav-item v-if="jwt" to="/cms/news">Vesti</b-nav-item>
              <b-nav-item v-if="jwt && !decoded.userType" to="/cms/users">Korisnici</b-nav-item>
              
              <b-nav-item v-if="!jwt" to="/login">Log In</b-nav-item>
              <b-nav-item v-else @click="logout()">Log Out</b-nav-item>
          
            </b-navbar-nav>

           <b-collapse/>


          </b-navbar>
    </div>

    <router-view/>
   
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import NewsListComponent from "./components/NewsListComponent.vue"
import { mapActions, mapState, mapMutations } from 'vuex';
export default {
  name: 'App',
  components: {
    HelloWorld, NewsListComponent
  },
 
  computed: {
      ...mapState([
        'jwt',
        'decoded',
        'categories'
      ])
    },
    methods: {
      ...mapActions([
        'fetchCategories'
      ]),
      
      ...mapMutations([
        'removeToken',
        'setToken',
        
      ]),
    
      logout() {
          this.removeToken();
          this.$router.push({ name: 'login' });

        },
   



    },
      mounted() {      
      if (localStorage.jwt) {
        this.setToken(localStorage.jwt);
        console.log(localStorage.jwt)
      }
      this.fetchCategories();
    },

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
