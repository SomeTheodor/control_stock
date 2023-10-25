const openModalButton = document.getElementById("openModalButton");
const openModalButton2 = document.getElementById("openModalButton2");
const closeModal = document.getElementById("closeModal");
const closeModal2 = document.getElementById("closeModal2");
const modal = document.getElementById("myModal");
const modal2 = document.getElementById("myModal2");

openModalButton.addEventListener("click", function() {
    modal.style.display = "block";
});

openModalButton2.addEventListener("click", function() {
    modal2.style.display = "block";
});

closeModal.addEventListener("click", function() {
    modal.style.display = "none";
});

closeModal2.addEventListener("click", function() {
    modal2.style.display = "none";
});

window.addEventListener("click", function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
    if (event.target === modal2) {
        modal2.style.display = "none";
    }
});
