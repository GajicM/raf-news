
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    jwt:localStorage.getItem('jwt') || null,    //isti hak kao dole videti
    decoded:  '', //crazy hack nisma siguran da treba ovako ali ovde se ispravlja ako bude zajebavao decoded/jwt
    news:[],
    post:null,
    categories:[],
    categoryNews:[],
    comments:[],
    mostRead:[],
    recent:[],
    tagNews:[],
    tags:[],
    users:[]
  },
  getters: {
  },
  
  mutations: {
    setToken(state, jwts) {
      if(jwts.msg)
      console.log(jwts.msg)
      console.log(jwts)
      state.jwt = jwts;
      localStorage.jwt = jwts;
      try {
     
        const payload = JSON.parse(atob(jwts.split('.')[1]));
        state.decoded=payload;
     
      } catch (error) {
        console.log('Error decoding JWT:', error);
        return null;
      }
    },

    removeToken(state) {
      state.jwt = '';
      localStorage.jwt = '';
      state.decoded=null;
    },
    setNews(state,news){
      state.news = news;
    },
    setTags(state,tags){
      state.tags=tags;
    },
    setPost(state,post){
      state.post = post;
    },
    setCategories(state,categories){
        state.categories=categories;
    },
    setCategoryNews(state,categoryNews){
      state.categoryNews=categoryNews;
    },
    setComments(state,comments){
      state.comments=comments;
    },
    setMostRead(state,news){
      state.mostRead=news;
    },
    addComment(state,comment){
      state.comments.push(comment);
    },
    setRecentNews(state,recent){
      state.recent=recent;
    },
    setTagNews(state,tagNews){
      state.tagNews=tagNews;
    },
    setChangedCategory(state,category){
      state.categories.forEach(element => {
        console.log(element)
        if(element.id==category.id){
        element.name=category.name;
        element.description=category.description;}
      })
      console.log(state.categories)
    },
    setDeleteCategory(state,id){
      let x;
      state.categories.forEach(element => {
        if(element.id==id)
        x=element;
      } )
      state.categories.splice(state.categories.indexOf(x),1);
    },
    addCategory(state,cat){
      state.categories.push(cat);
    },
    setAddNews(state,news){
      state.news.push(news);
      state.recent.pop();
      state.recent.push(news);
    },
    setChangedNews(state,news){ 
      state.news.forEach(element => {
     
      if(element.id==news.id){
      element.title=news.title;
      element.text=news.text;
      element.category=news.category;
      element.tags=news.tags;}
    })
    console.log(state.news)
  },

    setDeleteNews(state,id){
      let x;
      state.news.forEach(element => {
        if(element.id==id)
        x=element;
      } )
      state.news.splice(state.news.indexOf(x),1);
    },
    setUsers(state,users){
      state.users=users;
    },
    setChangedUser(state,user){
      state.users.forEach(element => {
     
        if(element.id===user.id){
        element.firstName=user.firstName;
        element.lastName=user.lastName;
        element.userType=user.userType;
        element.email=user.email;
        element.userStatus=user.userStatus
        }
      })
      console.log(state.users)
    },
    setAddUser(state,user){
      state.users.push(user);
    }
  },
  actions: {
    login({commit},obj){
      return new Promise( (resolve, reject) => {  
      fetch('http://localhost:8080/api/users/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
    })
        .then( res => res.json() )
        .then( res=>{
          if(res.jwt)   //res.jwt
          commit('setToken',res.jwt);
          resolve(res);
        })
        .catch(err=>console.log(err))
      })
    },
    fetchNews({commit},obj){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/news/getAllNews', { //TODO
          method: 'GET',
      })
          .then( res => res.json() )
          .then( res=>{
            
            commit('setNews',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchNewsById({commit},id){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/news/'+id, { //TODO
          method: 'GET',
          
          
      })
          .then( res => res.json() )
          .then( res=>{
            
            commit('setPost',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchCategories({commit}){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/category/getAllCategories', { //TODO
          method: 'GET',
      })
          .then( res => res.json() )
          .then( res=>{
            
            commit('setCategories',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchNewsByCategory({commit},id){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/news/getNewsByCategory/'+id, { //TODO
          method: 'GET',
      })
          .then( res => res.json() )
          .then( res=>{
            
            commit('setCategoryNews',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchCommentsByNews({commit},id){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/comments/getByNews/'+id, { //TODO
          method: 'GET',
      })
          .then( res => res.json() )
          .then( res=>{
            
            commit('setComments',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchMostReadNews({commit},id){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/news/getMostReadNews', { //TODO
          method: 'GET',
      })
          .then( res => res.json() )
          .then( res=>{
            
            commit('setMostRead',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },

    fetchAddComment({commit},obj){
      return new Promise( (resolve, reject) => {  
        //alternativno http://127.0.0.1:9000/register
      fetch('http://localhost:8080/api/comments/addComment', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(obj)
        })  
      .then( obj => obj.json() )
      .then( res => {
        if(res.comment)
        commit('addComment',res);
        resolve(res);
      }).catch(err=>console.log(err));
      });
    },
    fetchRecent({commit}){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/news/getRecentNews', { //TODO
          method: 'GET',
      })
          .then( res => res.json() )
          .then( res=>{
            
            commit('setRecentNews',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchNewsByTag({commit},id){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/news/getNewsByTag/'+id, { //TODO
          method: 'GET',
      })
          .then( res => res.json() )
          .then( res=>{
            
            commit('setTagNews',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchChangeCategory({commit},category){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/category/changeCategory', { //TODO
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' , 'Authorization': `Bearer ${this.state.jwt}`},
          body: JSON.stringify(category)
      })
          .then( res => res.json() )
          .then( res=>{
            commit('setChangedCategory',res);
          
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchDeleteCategory({commit},id){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/category/removeCategory/'+id, { //TODO
          method: 'DELETE',
          headers: {  'Authorization': `Bearer ${this.state.jwt}`},
          
      })
      .then( res => res.json() )
      .then( res=>{

          if(!res.message)
         commit('setDeleteCategory',id);
       
         resolve(res);
       })
     
           
          .catch(err=>console.log(err))
        })
    },
    fetchAddCategory({commit},obj){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/category/addCategory', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${this.state.jwt}` },
            body: JSON.stringify(obj)
        })  
      .then( obj => obj.json() )
      .then( res => {
        if(res.id)
        commit('addCategory',res);
        resolve(res);
      }).catch(err=>console.log(err));
      });
    },
    fetchChangeNews({commit},news){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/news/changeNews', { //TODO
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' , 'Authorization': `Bearer ${this.state.jwt}`},
          body: JSON.stringify(news)
      })
          .then( res => res.json() )
          .then( res=>{
            commit('setChangedNews',res);
          
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchChangeTagsInNews({commit},news){
      console.log('skedece')
      console.log(JSON.stringify(news));
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/news/changeTagsInNews', { 
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' ,'Authorization': `Bearer ${this.state.jwt}` },
          body: JSON.stringify(news)
      })
          .then( res => res.json()  )
          .then( res=>{
            console.log(res)
            
            
           commit('setChangedUsers',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchDeleteNews({commit},id){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/news/deleteNews/'+id, { //TODO
          method: 'DELETE',
          headers: { 'Authorization': `Bearer ${this.state.jwt}` },
          
      })
      .then( res => res.json() )
      .then( res=>{

          if(res)
         commit('setDeleteNews',id);
       
         resolve(res);
       })

          .catch(err=>console.log(err))
        })
    },
    fetchAddNews({commit},news){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/news/addNews', { //TODO
          method: 'POST',
          headers: { 'Content-Type': 'application/json' ,'Authorization': `Bearer ${this.state.jwt}` },
          body: JSON.stringify(news)
      })
          .then( res => res.json() )
          .then( res=>{
            commit('setAddNews',res);
          
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchUsers({commit}){
     
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/users', { //TODO
          method: 'GET',
          headers: { 'Authorization': `Bearer ${this.state.jwt}` },
      })
          .then( res => res.json() )
          .then( res=>{
            
            commit('setUsers',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
  /*  fetchUsers({commit},query){
    
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/users/getAllUsers?'+new URLSearchParams(query).toString(), { //TODO
          method: 'GET',
          headers: { 'Authorization': `Bearer ${this.state.jwt}` },
      })
          .then( res => res.json() )
          .then( res=>{
            
            commit('setUsers',res);//TODO  
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },*/
    fetchChangeUser({commit},news){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/users/changeUser', { 
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' ,'Authorization': `Bearer ${this.state.jwt}` },
          body: JSON.stringify(news)
      })
          .then( res => res.json() )
          .then( res=>{
            commit('setChangedUser',res);
          
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchChangeUserStatus({commit},news){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/users/changeUserStatus', { 
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' ,'Authorization': `Bearer ${this.state.jwt}` },
          body: JSON.stringify(news)
      })
          .then( res => res.json() )
          .then( res=>{
            commit('setChangedUser',res);
          
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    fetchAddUser({commit},user){
      return new Promise( (resolve, reject) => {  
        fetch('http://localhost:8080/api/users/addUser', { //TODO
          method: 'POST',
          headers: { 'Content-Type': 'application/json' ,'Authorization': `Bearer ${this.state.jwt}` },
          body: JSON.stringify(user)
      })
          .then( res => res.json() )
          .then( res=>{
            if(res.message)
            alert(res.message)
            else
            commit('setAddUser',res);
          
            resolve(res);
          })
          .catch(err=>console.log(err))
        })
    },
    


  },
  
 
    
      
      
    

        
        
  
  modules: {
  },

})
