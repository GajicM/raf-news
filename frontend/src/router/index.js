import Vue from 'vue'
import VueRouter from 'vue-router'
import LogIn from "@/views/Login.vue"
import HomePage from '@/views/HomePage.vue'
import PostPage from "../views/PostPage..vue"
import NewsByCategory from "@/views/NewsByCategory.vue"
import MostReadPageVue from '@/views/MostReadPage.vue'
import NewsByTag from "@/views/NewsByTag.vue"
import CategoriesVue from '@/views/CMS/Categories.vue'
import NewsVue from '@/views/CMS/News.vue'
import UserVue from '@/views/CMS/Users.vue'
import AddUserVue from '@/views/CMS/AddUser.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomePage
  },
  {
    path: '/login',
    name: 'login',
    component: LogIn
  },
  {
    path: '/news/:id',
    name: 'post',
    component: PostPage
  },
  {
    path: '/news/getNewsByCategory/:id',
    name:'newsCategories',
    component:NewsByCategory
  },
  {
    path:'/mostRead',
    name:'mostRead',
    component:MostReadPageVue
  },
  {
    path:`/news/getNewsByTag/:id`,
    name:'newsTag',
    component:NewsByTag
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:`/cms/categories`,
    name:'cmsCategories',
    component:CategoriesVue,
    meta:{
      authRequired:true,
    }
  },
  {
    path: '/cms/categories/addCategory',
    name: 'addCategory',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "addCategory" */ '@/views/CMS/AddCategory.vue'),
    meta:{
      authRequired:true,
    }
  },
  {
    path:`/cms/news`,
    name:'cmsNews',
    component:NewsVue,
    meta:{
      authRequired:true,
    }
  },
  {
    path: '/cms/news/addNews',
    name: 'addNews',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "addNews" */ '@/views/CMS/AddPost.vue')
    ,
    meta:{
      authRequired:true,
    }
  },
  {
    path:`/cms/users`,
    name:'cmsUser',
    component:UserVue
    ,
    meta:{
      authRequired:true,
      adminRequired:true
    }
  },
 
  {
    path:`/cms/users/addUser`,
    name:'cmsAddUser',
    component:AddUserVue
    ,
    meta:{
      authRequired:true,
      adminRequired:true
    }
  },
 
  {
    path:`/cms/news/filterByCategories/:id`,
    name:'cmsFilterByCategories',
    component:NewsVue
    ,
    meta:{
      authRequired:true,
      adminRequired:true
    }
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.authRequired) {
    const jwt = localStorage.getItem('jwt');

    if (!jwt) {
      next({name: 'login'}); //TODO OVDE
      return;
    }

    const payload = JSON.parse(atob(jwt.split('.')[1]));
    console.log(payload)
    const expDate = new Date(payload.exp * 1000);
    if (expDate < new Date()) {
      next({name: 'login'});
      return;
    }
    if(to.meta.adminRequired && payload.userType){
     alert("You do not have privileges")
     next({name: 'home'});
      return;
    }

  }

  next();
});

export default router
