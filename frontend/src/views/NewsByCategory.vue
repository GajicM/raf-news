<template>
    <div id="app">
      <Header subtitle="Home"/>
        <NewsListComponent :news="this.categoryNews"/>
      
    </div>
  </template>
  
  <script>
  
import NewsListComponent from '@/components/NewsListComponent.vue';
import { mapActions,mapMutations,mapState} from 'vuex';
  
    export default {
      name: 'NewsByCategory',
      
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
        'fetchNewsByCategory'
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
        'categoryNews'
      ])
    },
    watch: {
       $route() {
        this.id = this.$route.params.id;
      
       
      this.fetchNewsByCategory(this.id).then(()=>{
        console.log(this.id);
       })

      
        
      }
    },
  mounted() {

      console.log(this.$route.params.id)  
      this.id = this.$route.params.id;
        console.log(this.id)
       this.fetchNewsByCategory(this.id);
    
    },

  }

  </script>
  
  <style scoped>
  
  </style>
  