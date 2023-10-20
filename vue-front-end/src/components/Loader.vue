<template>
    <div class="al-spinner">
        <i class="fa-solid fa-spinner text-primary"></i>
    </div>
</template>

<script setup>

</script>

<style scoped>
    .al-spinner {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }

    .fa-spinner {
        animation: al-rotate 2s infinite;
        font-size: 6rem;
    }

    @keyframes al-rotate {
        from {
            transform: rotate(0);
        }
        to {
            transform: rotate(359deg);
        }
    }
</style>