<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-light px-lg-2">
        <div class="container-fluid">
          <div class="navbar-brand logo-container me-md-5">
            <router-link to="/">
              <img :src="getImage('logo.png')" alt="logo">
            </router-link>
          </div>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <router-link to="/" class="nav-link active" aria-current="page">Home</router-link>
              </li>

              <li class="nav-item">
                <router-link to="/contacts" class="nav-link active" aria-current="page">Contattaci</router-link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
</template>

<script setup>

    function getImage(name) {
        return new URL(location.origin + "/src/assets/images/" + name)
    }
</script>

<style lang="scss" scoped>
    li {
        transition: background-color var(--al-transition-short);
    }

    li:hover {
        background-color: var(--al-light-hover);
    }

    .logo-container {
      width: 180px;
      overflow: hidden;
    }

    img {
      object-fit: cover;
      width: 100%;
      height: 100%;
    }
</style>