<template>
    <div id="app">
      <Header subtitle="Home"/>
        <NewsListComponent :news="this.recent"/>
      
    </div>
  </template>
  
  <script>
  
import NewsListComponent from '@/components/NewsListComponent.vue';
import { mapActions,mapMutations,mapState} from 'vuex';
  
    export default {
      name: 'HomePage',
      
      components: {
    NewsListComponent
  },
  methods: {
      ...mapActions([
        'fetchNews',
        'fetchRecent'
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
        'recent'
      ])
    },
  mounted() {
      //  this.subtitle = this.$route.params.name;
      //  this.depID = this.$route.params.id;

       this.fetchRecent();
    
    },

  }

  </script>
  
  <style scoped>
  
  </style>
  