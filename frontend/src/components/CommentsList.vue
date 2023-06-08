<template>
    <div class="hello">
      <b-list-group>
      <b-container class="CommentsList">
        
      <!--ovde staviti href=shops/:id i ime -->
      <b-col v-for="dep in comments" :key="dep.id" >
        <b-list-group-item   >
           <h1>{{dep.username}} </h1> 
           <h2>{{ dep.comment }}</h2> 
        <h3>Date:{{ new Date(dep.timeCreated).toLocaleString()  }}</h3>
        </b-list-group-item>
      </b-col> <!--src ce biti u bazi-->
     
      </b-container>
      </b-list-group>
    
      
      
  
    </div>
  </template>
  
  <script>
  export default {
    name: 'CommentsList',
    props: {
      comments:[],
    },
  
  
  }
    
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  h3 {
    margin: 40px 0 0;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
  </style>
  