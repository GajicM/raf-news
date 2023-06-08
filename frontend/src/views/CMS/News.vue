

<template>
 <div class="categories">
    <h1 class="mt-4">News</h1>

    <div class="row">
      <div class="col-6">
        <b-table
          :items="shownNews"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          :sort-by.sync="sortBy"
          :sort-desc.sync="sortDesc"
          :sort-direction="sortDirection"
        >

          <template #cell(title)="row">
            <a style="color: blue; cursor: pointer;" @click="openNewTab(row.item.id)">{{row.item.title}}</a>
          </template>

          <template #cell(author)="row">
          {{row.item.author.firstName + " "+ row.item.author.lastName}}
          </template>
          <template #cell(text)="row">
          {{row.item.text | shortText}}
          </template>

          <template #cell(date)="row">
          {{new Date(row.item.date).toLocaleString()}}
          </template>

          <template #cell(category)="row">
          {{row.item.category.name}}
          </template>

          <template #cell(tags)="row">
            {{ row.item.tags | tagsName }}
          </template>

          <template #cell(actionUpdate)="row">
            <b-button variant="danger" @click="selectedPost = row.item" :key="'update'+row.item.id">Update</b-button>
          </template>

          <template #cell(actionDelete)="row">
            
            <b-button variant="danger" @click="deleteNews(row.item.id)" :key="'delete'+row.item.id">Delete</b-button>
          </template>
          
          <!-- Table cell templates here -->
        </b-table>
        <b-pagination
          v-model="currentPage"
          :total-rows="news.length"
          :per-page="perPage"
        
          class="mt-3"
        ></b-pagination>
        <b-button variant="success" :to="'/cms/news/addNews'">New Post</b-button>
      </div>
      <div class="col-6">
        <Post v-if="selectedPost && news.includes(selectedPost)" :form="selectedPost" :categories="categories" />
      </div>
    </div>
  </div>
  </template>
  
  <script>
  import Post from "@/components/CMS/Post.vue";
  import { mapActions,mapMutations,mapState } from "vuex";
  export default {
    name:'News',
    components: {
      Post
    },
    filters: {
      shortText(value) {
        if (value.length < 30) {
          return value;
        }
        return value.slice(0, 30) + '...'
      },
      tagsName(value) {
        console.log(value)
        let retVal=[];
            value.forEach(element => {
                retVal.push(element.tag);
            });
            return retVal.toString();
      }
    },
    data() {
      return {
        selectedPost: null,
        shownNews: [],
        perPage: 10, // Number of items per page
        currentPage: 1, // Current page number
      sortBy: '', // Field to sort by
      sortDesc: false, // Sort direction (ascending or descending)
      sortDirection: 'asc', // Default sort direction
      fields: [
        { key: 'id', label: 'ID' },
        { key: 'title', label: 'Title', sortable: true },
        { key: 'text', label: 'Text' },
        { key: 'author', label: 'Author' },
        { key: 'category', label: 'Category' },
        { key: 'tags', label: 'Tags' },
        { key: 'date', label: 'Created At' },
        { key: 'actionUpdate', label: 'Update' },
        { key: 'actionDelete', label: 'Delete' }
      ], // Table field definitions
      selectedPost: null
      }
    },
    methods:{
      ...mapActions([
        'fetchNews',
        'fetchDeleteNews',
        'fetchGetAllTags',
        'fetchNewsByCategory'
      ]),

      ...mapMutations([
       
      ]),
      deleteNews(id){
        this.fetchDeleteNews(id).then((res)=>{
          if(res)
          alert("Succesfully deleted news with id:"+id);
        })
      
      },
      openNewTab(id) {
      
      window.open('http://localhost:8081/news/'+id, '_blank');
      
      
    }
    
    
    }
      , 
      computed: {
      ...mapState([
        'news',
        'jwt',
        'mostRead',
        'categories',
        'tags',
        'categoryNews'
      ])
    },mounted(){
    
        //RECIKLIRAM VIEW JER STO DA NE ICBA VISE

      if(this.$route.params.id==undefined ){
        this.fetchNews().then(()=>{
          this.shownNews=this.news;
        });
        
      }
      else
      {
        this.fetchNewsByCategory(this.$route.params.id).then(()=>{
          this.shownNews=this.categoryNews;
        });
       
      }
     console.log(this.shownNews)
   //  this.fetchGetAllTags();
    },
    watch: {
    $route(newRoute, oldRoute) {
      // Handle route changes here
      console.log('New Route:', newRoute);
      console.log('Old Route:', oldRoute);
      if(newRoute.params.id==undefined)
         this.fetchNews().then(()=>{
          this.shownNews=this.news;
        });
        else{
          this.fetchNewsByCategory(this.$route.params.id).then(()=>{
          this.shownNews=this.categoryNews;
        });
        }
      
      // You can perform any necessary actions based on the route change
      // For example, update data or make API calls
    }
  },
  }
      
    
  
  </script>
  