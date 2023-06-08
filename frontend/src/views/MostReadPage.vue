<template>
    <div id="app">
      <Header subtitle="Home"/>
      <h1>Najcitanije vesti!</h1>
        <NewsListComponent :news="this.mostRead"/>
      
    </div>
  </template>
  
  <script>
  
import NewsListComponent from '@/components/NewsListComponent.vue';
import { mapActions,mapMutations,mapState} from 'vuex';
  
    export default {
      name: 'MostReadPage',
      
      components: {
    NewsListComponent
  },
  data() {
      return {
        id:'',
        
      }
    },
  methods: {
      ...mapActions([
        'fetchMostReadNews'
      ]),

      ...mapMutations([
        'removeToken',
        'setToken'
      ])}
      , 
      computed: {
      ...mapState([
        'news',
        'jwt',
        'mostRead'
      ])
    },
    watch: {
       $route() {
        this.id = this.$route.params.id;
      
       
      this.fetchMostReadNews(this.id).then(()=>{
        console.log(this.id);
       })

      
        
      }
    },
  mounted() {

      console.log(this.$route.params.id)  
      this.id = this.$route.params.id;
        console.log(this.id)
       this.fetchMostReadNews(this.id);
    
    },

  }

  </script>
  
  <style scoped>
  
  </style>
  