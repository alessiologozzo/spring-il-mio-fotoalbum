<template>
    <div>
        <Navbar />

        <div class="container d-flex justify-content-center pt-2">
            <form id="contact-form" class="col-11 col-sm-9 col-md-7 col-lg-6 col-xl-5 col-xxl-4 d-flex flex-column gap-3 bg-light p-4 mt-5 al-rounded-border al-shadow">
                <h5>Contattaci</h5>

                <div class="d-flex flex-column gap-1">
                    <label for="email">Email</label>
                    <input type="email" id="email" required minlength="3" maxlength="50" placeholder="Email..." v-model="contactDTO.email">
                </div>

                <div class="d-flex flex-column gap-1">
                    <label for="message">Message</label>
                    <input type="text" id="message"  required minlength="3" maxlength="255" placeholder="Message..." v-model="contactDTO.message">
                </div>

                <div class="d-flex justify-content-end pt-3">
                    <div @click="checkValidity()" class="btn btn-primary">Invia</div>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup>
    import Navbar from "../components/Navbar.vue"
    import { ref } from 'vue'
    import axios from 'axios'
    import { useRouter } from 'vue-router'

    const router = useRouter()
    const contactDTO = ref({email: "", message: ""})

    function checkValidity() {
        const form = document.getElementById("contact-form")

        if(!form.checkValidity())
            form.reportValidity()
        else
            storeContact()
    }

    function storeContact() {
        console.log(contactDTO.value)
        axios.post("http://localhost:8080/api/contacts", contactDTO.value)
        .then(res => router.push("/"))
        .catch(err => {
            const emailElement = document.getElementById("email")
            const messageElement = document.getElementById("message")

            emailElement.value = "";
            emailElement.classList.add("form-control", "is-invalid")

            messageElement.value = "";
            messageElement.classList.add("form-control", "is-invalid")
        })
    }

</script>

<style scoped>
    input[type="text"],
    input[type="email"] {
        width: 100%;
        background-color: aliceblue;
        color: black;
        border: 1px solid black;
        border-radius: 5px;
        padding: 0.3rem 0.2rem;
    }
</style>
