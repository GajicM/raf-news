<template>
    <div class="categories">
    <h1 class="mt-4">Users</h1>

    <div class="row">
      <div class="col-6">
        <b-table
          :items="users"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          :sort-by.sync="sortBy"
          :sort-desc.sync="sortDesc"
          :sort-direction="sortDirection"
        >

          <template #cell(id)="row">
           {{ row.item.id }}
          </template>

          <template #cell(email)="row">
          {{row.item.email  }}  
          </template>

          <template #cell(firstName)="row">
            {{ row.item.firstName | capitalize}}
          </template>


          <template #cell(lastName)="row">
            {{ row.item.lastName }}
          </template>
         

         
          <template #cell(userType)="row">
          {{ row.item.userType }}
          </template>

          <template #cell(userStatus)="row">
            {{ row.item.userStatus }}
          </template>

          <template #cell(actionUpdate)="row">
            <b-button  variant="danger" @click="{selectedUser = row.item;}" :key="'update'+row.item.id" >Update</b-button>
          
          </template>
          <template #cell(actionChangeStatus)="row">
            <b-button  variant="danger" @click="changeStatus(row.item)" :key="'changeStatus'+row.item.id">Change Status</b-button>
          
          </template>
          
          <!-- Table cell templates here -->
        </b-table>
        <b-pagination
          v-model="currentPage"
          :total-rows="users.length"
          :per-page="perPage"
        
          class="mt-3"
        ></b-pagination>
        <b-button variant="success" :to="'/cms/users/addUser'">New User</b-button>
      </div>
      <div class="col-6">
        <User v-if="selectedUser && users.includes(selectedUser)" :form="selectedUser"  />
      </div>
    </div>
  </div>
  </template>
  
  <script>
  import User from "@/components/CMS/User.vue";
  import { mapActions,mapMutations,mapState } from "vuex";
  export default {
    name:'Categories',
    components: {
        User
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
        selectedUser: null,
        fields: [
          {key:'id',label:'ID'},
        { key: 'firstName', label: 'First Name' },
        { key: 'lastName', label: 'Last Name' },
        { key: 'userType', label: 'Type' },
        { key: 'userStatus', label: 'Status' },
        { key: 'actionUpdate', label: 'Update' },
        { key: 'actionChangeStatus', label: 'Change User Status' }
      ],
      currentPage: 1,
      perPage: 5,
      sortBy: '',
      sortDesc: false,
      sortDirection: 'asc',
      }
    },
    methods:{
      ...mapActions([
        'fetchUsers',
      
        'fetchChangeUserStatus'
      ]),

      ...mapMutations([
       
      ]),
     
      changeStatus(u){
      
        this.fetchChangeUserStatus(u).then((res)=>{
            alert("status changed");
        })
      }
    
    
    }
      , 
      computed: {
      ...mapState([
        'jwt',
        'users'
      ])
    },mounted(){
     /*   let obj={
          offset:(this.currentPage-1)*this.perPage,
          limit:this.perPage
        }
        console.log(obj)
     this.fetchUsers(obj)*/
     this.fetchUsers()
     console.log(this.users)
    }
  }
      
    
  
  </script>
  