<template>
    <div id="app">
      <Header subtitle="Home"/>
        <NewsListComponent :news="this.tagNews"/>
      
    </div>
  </template>
  
  <script>
  
import NewsListComponent from '@/components/NewsListComponent.vue';
import { mapActions,mapMutations,mapState} from 'vuex';
  
    export default {
      name: 'NewsByTag',
      
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
        'fetchNewsByTag'
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
        'tagNews'
      ])
    },
    watch: {
       $route() {
        this.id = this.$route.params.id;
      
       
      this.fetchNewsByTag(this.id).then((res)=>{
        console.log(this.id);
        console.log(res)
       })

      
        
      }
    },
  mounted() {

      console.log(this.$route.params.id)  
      this.id = this.$route.params.id;
        console.log(this.id)
       this.fetchNewsByTag(this.id).then((res)=>{
        console.log(this.id);
        console.log(res)
       });
    
    },

  }

  </script>
  
  <style scoped>
  
  </style>
  