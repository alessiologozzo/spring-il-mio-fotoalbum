// let ids = []
let selectedIngredients = ""
let photos = [];

function setDeleteAction(name, action) {
	const title = document.getElementById("deleteModal").getElementsByTagName("h5")
	title[0].textContent = name


    console.log(name)
    console.log(action)
	const form = document.getElementById("delete-form")
	form.action = action
}

function submitDeleteForm() {
	const form = document.getElementById("delete-form")
	form.submit()
}

function setTipsBottomPosition() {
	const resultBox = document.getElementById("result-box")
	resultBox.style.bottom = "-" + resultBox.offsetHeight + "px"
}

function shouldShowTips(event) {
	filterTips()
	if (countActiveTips() > 0) {
		const resultBox = document.getElementById("result-box")
		resultBox.classList.remove("d-none")
		setTipsBottomPosition()
		event.stopPropagation()
	}
	else
		hideTips()
}

function filterTips() {
	const resultBox = document.getElementById("result-box")
	const tips = resultBox.getElementsByTagName("li")
	const searchInput = document.getElementById("search").value

	for (let i = 0; i < tips.length; i++) {
		if (tips[i].textContent.toLowerCase().includes(searchInput.toLowerCase()) && !selectedIngredients.toLowerCase().includes(tips[i].textContent.toLowerCase()))
			tips[i].classList.remove("d-none")
		else
			tips[i].classList.add("d-none")
	}
	setTipsBottomPosition()
}

function hideTips() {
	const resultBox = document.getElementById("result-box")
	resultBox.classList.add("d-none")
}

function setSelectedIngredients(ingredients) {
	selectedIngredients = ingredients
}

function filterSelectedIngredients() {
	const selectedIngredientsElement = document.getElementById("selected-ingredients")
	const ingredientBadges = selectedIngredientsElement.getElementsByClassName("badge")
	const noSelectedIngredients = document.getElementById("no-selected-ingredients")
	let counter = 0

	for (let i = 0; i < ingredientBadges.length; i++) {
		if (selectedIngredients.toLowerCase().includes(ingredientBadges[i].textContent.toLowerCase())) {
			ingredientBadges[i].classList.remove("d-none")
			counter++
		}
		else
			ingredientBadges[i].classList.add("d-none")
	}

	if (counter > 0)
		noSelectedIngredients.classList.add("d-none")
	else
		noSelectedIngredients.classList.remove("d-none")
}

function addIngredient(name) {
	const searchBar = document.getElementById("search")
	searchBar.value = ""

	if (selectedIngredients.length > 0)
		selectedIngredients += ", " + name
	else
		selectedIngredients += name

	filterSelectedIngredients()
}

function countActiveTips() {
	const resultBox = document.getElementById("result-box")
	const tips = resultBox.getElementsByTagName("li")
	let result = 0

	for (let i = 0; i < tips.length; i++)
		if (!tips[i].classList.contains("d-none"))
			result++

	return result
}

function removeIngredient(name) {
	if (selectedIngredients.includes(name)) {
		if (selectedIngredients.includes(" " + name + ","))
			selectedIngredients = selectedIngredients.replace(" " + name + ",", "")
		else if (selectedIngredients.includes(name + ","))
			selectedIngredients = selectedIngredients.replace(name + ",", "")
		else if (selectedIngredients.includes(name))
			selectedIngredients = selectedIngredients.replace(name, "")

		if (selectedIngredients[selectedIngredients.length - 2] == "," || selectedIngredients[selectedIngredients.length - 2] == " ")
			selectedIngredients = selectedIngredients.substring(0, selectedIngredients.length - 2)

		if (selectedIngredients[0] == " ")
			selectedIngredients = selectedIngredients.substring(1)

		filterSelectedIngredients()
	}
}

function submitCategories() {
    const inputElement = document.getElementById("categories-input");
    inputElement.value = selectedIngredients

    const form = document.getElementById("categories-form")
    if(form.checkValidity())
        form.submit()
    else
        form.reportValidity()
}

function setPhotos(photosJSON) {
	photos = photosJSON
}

function toggleVisibility(elementId, id) {
	const element = document.getElementById(elementId)
	if(element.classList.contains("fa-check")) {
		element.classList.remove("fa-check", "al-text-success")
		element.classList.add("fa-xmark", "al-text-danger")
	}
	else {
		element.classList.remove("fa-xmark", "al-text-danger")
		element.classList.add("fa-check", "al-text-success")
	}

	for(let i = 0; i < photos.length; i++)
		if(photos[i].id == id) {
			photos[i].visible = !photos[i].visible
			break
		}
}

function submitVisibles(elementId) {
	const form = document.getElementById(elementId)
	const inputElement = document.getElementById("visibles-input")

	inputElement.value = JSON.stringify(photos)
	form.submit()
}