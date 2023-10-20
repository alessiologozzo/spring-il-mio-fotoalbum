<template>
  <div>
    <Navbar />

    <h1 class="text-center pt-4">FotoAlbum</h1>
    <Loader v-if="loading" />
    <div v-else>
        <div class="container">
          <div class="d-flex justify-content-end pt-4">
            <div class="d-flex flex-column gap-1 bg-light al-rounded-border al-shadow p-4 mb-5">
                <label for="name">Cerca per nome</label>
                <input type="text" v-model="photoFilter" id="name" placeholder="Cerca per nome...">
                <div class="d-flex justify-content-end  gap-3 pt-3">
                  <div @click="photoFilter = ''; getPhotos()" class="btn btn-success mt-2">Refresh</div>
                  <div @click="getPhotos()" class="btn btn-primary mt-2">Filtra</div>
                </div>
            </div>
        </div>
          <div class="row">
            <div v-for="photo in photos" :key="photo.id" class="col-12 col-sm-6 col-lg-4 col-xl-3 mt-4 p-3">
              <h6>{{photo.name}}</h6>
              <div class="img-container">
                <img :src="photo.url" :alt="photo.name">
              </div>
            </div>
          </div>
        </div>
    </div>
  </div>
</template>

<script setup>
  import Navbar from "../components/Navbar.vue"
  import Loader from "../components/Loader.vue"
  import axios from 'axios'
  import { ref } from 'vue'

  const photos = ref(null)
  const loading = ref(true)
  const photoFilter = ref("")

  getPhotos()

  function getPhotos() {
    loading.value = true
    axios.get(photoFilter.value == "" ? "http://localhost:8080/api/photos" : "http://localhost:8080/api/photos?name=" + photoFilter.value)
      .then(res => {
        photos.value = res.data
        loading.value = false
        })
      .catch(err => console.log(err))
  }

</script>

<style scoped>
.img-container {
  width: 100%;
  height: 100%;
  border: 4px groove lightslategray;
  border-radius: 7px;
}

.img-container img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
}
</style>