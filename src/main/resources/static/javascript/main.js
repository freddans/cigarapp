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
// function changeReadMore(event) {
//     const content = event.target.closest('.content');
//     const moreContent = content.querySelector('.content-more');
//
//     // Toggle the visibility of the hidden content
//     moreContent.classList.toggle('hidden');
//
//     // Update the button text based on the presence of the 'hidden' class
//     if (moreContent.classList.contains('hidden')) {
//         event.target.textContent = 'Read More';
//     } else {
//         event.target.textContent = 'Read Less';
//     }
// }
//
// // Add event listener to all "Read More" buttons
// document.querySelectorAll('.content a').forEach(button => {
//     button.addEventListener('click', changeReadMore);
// });

// AAA
// function changeReadMore() {
//     const mycontent = document.getElementById('content-moreid');
//     const mybutton = document.getElementById('myLinkId');
//
//     // Toggle the visibility of the hidden content
//     mycontent.classList.toggle('hidden');
//
//     // Update the button text based on the presence of the 'hidden' class
//     if (mycontent.classList.contains('hidden')) {
//         mybutton.textContent = 'Read More';
//         mycontent.style.display = 'none';
//     } else {
//         mybutton.textContent = 'Read Less';
//         mycontent.style.display = 'block';
//     }
// }
//
// document.getElementById('myLinkId').addEventListener('click', changeReadMore);

// Function to toggle visibility of extra content
function changeReadMore(buttonId, contentId) {
    const mycontent = document.getElementById(contentId);
    const mybutton = document.getElementById(buttonId);

    // Toggle the visibility of the hidden content
    mycontent.classList.toggle('hidden');

    // Update the button text based on the presence of the 'hidden' class
    if (mycontent.classList.contains('hidden')) {
        mybutton.textContent = 'Read More';
        mycontent.style.display = 'none';
    } else {
        mybutton.textContent = 'Read Less';
        mycontent.style.display = 'block';
    }
}

// Add event listeners to all "Read More" buttons
document.addEventListener('DOMContentLoaded', function () {
    const readMoreButtons = document.querySelectorAll('.content a');
    readMoreButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            event.preventDefault(); // Prevent default link behavior
            const contentId = button.getAttribute('data-content-id');
            changeReadMore(button.id, contentId);
        });
    });
});
