<template>
    <div class="hello">
      <b-list-group>
      <b-container class="NewsList">
        
      <b-row> <!--ovde staviti href=shops/:id i ime -->
      <b-col v-for="dep in news" :key="dep.id" >
        <b-list-group-item  :to="`/news/${dep.id}`" >
           <h1>{{dep.title}} </h1> 
           <h2>{{ dep.text }}</h2> 
        <h2>Category: {{ dep.category.name }}</h2>   
        <h3>Date:{{ new Date(dep.date).toLocaleString()  }}</h3>
        </b-list-group-item>
      </b-col> <!--src ce biti u bazi-->
      </b-row>
      </b-container>
      </b-list-group>
    
      
      
  
    </div>
  </template>
  
  <script>
  export default {
    name: 'NewsList',
    props: {
      news:[],
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
  