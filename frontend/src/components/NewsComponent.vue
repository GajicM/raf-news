<template>
    <div class="hello">
      <h1>{{post.title  }}</h1>
      <h2>Category: {{post.category.name  }}</h2>
      <h2> {{post.text}}</h2>
      <h2>{{ post.author.firstName  + " "+post.author.lastName}}</h2>
      <div v-for="tag in this.post.tags" :key="tag.id">
    <router-link :to="`/news/getNewsByTag/${tag.id}`"> <h2>{{ tag.tag }}</h2></router-link>
   
     
   
    </div>

      
      
  
    </div>
  </template>
  
  <script>
  export default {
    name: 'NewsComponent',
    props: {
        post:Object
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
  