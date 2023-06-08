<template>
    <div id="app">
      <Header subtitle="Home"/>
   
        <NewsComponent  :post="this.post"/>
      
        <CommentsList :comments="this.comments"/>
     
        <CommentForm :post="this.post"/>
      
    </div>
  </template>
  
  <script>
  
import NewsComponent from '@/components/NewsComponent.vue';
import CommentsList from '@/components/CommentsList.vue'
import CommentForm from '@/components/CommentForm.vue';
import { mapActions,mapMutations,mapState} from 'vuex';

  
    export default {
      name: 'PostPage',
      
      components: {
        NewsComponent,CommentsList,CommentForm
  }, 
   data() {
      return {
        id:'',
        
      }
    },
  methods: {
      ...mapActions([
        'fetchNewsById',
        'fetchCommentsByNews'
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
        'post',
        'comments',
      ])
    },
    watch: {
       $route() {
        this.id = this.$route.params.id;
      
       
      this.fetchNewsById(this.id).then(()=>{
        console.log(this.post);
       })
       this.fetchCommentsByNews(this.id);

      
        
      }
    },
    mounted() {
       console.log("OVDE JE")
       this.id = this.$route.params.id;
       
       if(!(this.post!=null && this.post!=undefined && this.post.id==this.id)){
       this.fetchNewsById(this.id).then(()=>{
        console.log(this.post);
       })
      }
       this.fetchCommentsByNews(this.id).then(()=>{
        console.log(this.comments);
       });


    
    
   
   }

  }

  </script>
  
  <style scoped>
  
  </style>
  