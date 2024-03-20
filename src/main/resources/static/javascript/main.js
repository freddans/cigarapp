const menuBtn = document.querySelector(".menu-btn");
const navigation = document.querySelector(".navigation");

menuBtn.addEventListener("click", () => {
    menuBtn.classList.toggle("active");
    navigation.classList.toggle("active");
});

const btns = document.querySelectorAll(".nav-btn");
const slides = document.querySelectorAll(".img-slide");
const contents = document.querySelectorAll(".content");

var sliderNav = function(manual) {
    btns.forEach((btn) => {
        btn.classList.remove("active");
    });

    slides.forEach((slide) => {
        slide.classList.remove("active");
    });

    contents.forEach((content) => {
        content.classList.remove("active");
    });

    btns[manual].classList.add("active");
    slides[manual].classList.add("active");
    contents[manual].classList.add("active");
}

    btns.forEach((btn, i) => {
        btn.addEventListener("click", () => {
            sliderNav(i)
        });
    });


// Function to toggle visibility of extra content
function changeReadMore(buttonId, contentId) {
    const mycontent = document.getElementById(contentId);
    const mybutton = document.getElementById(buttonId);
    const readMoreContainer = mybutton.parentElement;

    // Toggle the visibility of the hidden content
    mycontent.classList.toggle('hidden');

    // Update the button text based on the presence of the 'hidden' class
    if (mycontent.classList.contains('hidden')) {
        mybutton.textContent = 'Read More';
        mycontent.style.display = 'none';
        mybutton.style.zIndex = 'auto'; // Set z-index back to auto when content is hidden
    } else {
        mybutton.textContent = 'Read Less';
        mycontent.style.display = 'block';
        const contentHeight = mycontent.clientHeight;
        readMoreContainer.style.marginBottom = contentHeight + 'px'; // Adjust margin based on content height
        mybutton.style.zIndex = '999'; // Set a higher z-index when content is shown
    }
}

// Add event listeners to all "Read More" buttons
document.addEventListener('DOMContentLoaded', function () {
    const readMoreButtons = document.querySelectorAll('.read-more');
    readMoreButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            event.preventDefault(); // Prevent default link behavior
            const contentId = button.getAttribute('data-content-id');
            changeReadMore(button.id, contentId);
        });
    });
});