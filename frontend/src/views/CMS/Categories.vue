<template>
    <div class="categories">
    <h1 class="mt-4">Categories</h1>
  
    <div class="row">
      <div class="col-6">
        <b-table
          :items="categories"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          :sort-by.sync="sortBy"
          :sort-desc.sync="sortDesc"
          :sort-direction="sortDirection"
        >
          <template #cell(name)="row">
            <a :href="`/cms/news/filterByCategories/${row.item.id}`">{{ row.value }}</a>
          </template>
          <template #cell(actionUpdate)="row">
           
            <b-button variant="danger" @click="selectedCategory = row.item" :key="'update'+row.item.id">Update</b-button>
          </template>
        
          <template #cell(actionDelete)="row">
           
            <b-button variant="danger" @click="deleteCategory(row.item.id)" :key="'delete'+row.item.id">Delete</b-button>
         </template>
        </b-table>
        <b-pagination
          v-model="currentPage"
          :total-rows="categories.length"
          :per-page="perPage"
        style="align=center' "  
          class="mt-3"
        ></b-pagination>


        <b-button variant="success" :to="'/cms/categories/addCategory'">New Category</b-button>
      </div>
      <div class="col-6">
        <Category v-if="selectedCategory" :form="selectedCategory"/>
      </div>
    </div>
  </div>
  </template>
  
  <script>
  import Category from "@/components/CMS/Category.vue";
  import { mapActions,mapMutations,mapState } from "vuex";
  export default {
    name:'Categories',
    components: {
      Category
    },
    filters: {
      shortText(value) {
        if (value.length < 30) {
          return value;
        }
        return value.slice(0, 30) + '...'
      }
    },
    data() {
      return {
        perPage: 2, // Number of items per page
      currentPage: 1, // Current page number
      sortBy: '', // Field to sort by
      sortDesc: false, // Sort direction (ascending or descending)
      sortDirection: 'asc', // Default sort direction
      fields: [
        { key: 'id', label: 'ID' },
        { key: 'name', label: 'Name', sortable: true },
        { key: 'description', label: 'Description' },
        { key: 'actionUpdate', label: 'Update' },
        { key: 'actionDelete', label: 'Delete' }
      ], // Table field definitions
        selectedCategory: null,
        subjects: []
      }
    },
    methods:{
      ...mapActions([
        'fetchCategories',
        'fetchDeleteCategory'
      ]),

      ...mapMutations([
       
      ]),
      deleteCategory(id){
        this.fetchDeleteCategory(id).then((res)=>{
          console.log(res);
          if(!res.message)
          alert("Succesfully deleted category with id:"+id);
          else
          alert(res.message)
        })
      
      }
    
    
    }
      , 
      computed: {
      ...mapState([
        'categories',
        'jwt',
        'mostRead'
      ])
    },mounted(){
     this.fetchCategories();
    }
  }
      
    
  
  </script>
  