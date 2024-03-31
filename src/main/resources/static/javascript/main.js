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


// Function to toggle visibility of extra content and handle container close
function changeReadMore(buttonId, contentId) {
    const mycontent = document.getElementById(contentId);
    const mybutton = document.getElementById(buttonId);
    const readMoreContainer = mybutton.parentElement;
    const readMoreBox = document.getElementById('readMoreContainerBox');

    // Toggle the visibility of the hidden content
    mycontent.classList.toggle('hidden');

    // Update the button text based on the presence of the 'hidden' class
    if (mycontent.classList.contains('hidden')) {
        mybutton.textContent = 'Read More';
        mycontent.style.display = 'none';
        mybutton.style.zIndex = 'auto'; // Set z-index back to auto when content is hidden
        readMoreBox.style.display = 'none'; // Hide the read more container
    } else {
        mybutton.textContent = 'Read Less';
        mycontent.style.display = 'block';
        const contentHeight = mycontent.clientHeight;
        readMoreContainer.style.marginBottom = contentHeight + 'px'; // Adjust margin based on content height
        mybutton.style.zIndex = '999'; // Set a higher z-index when content is shown
        readMoreBox.style.display = 'block'; // Show the read more container
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

/* contact */
var contactLink = document.getElementById('contactLink');
var contactContainer = document.getElementById('contactContainer');

// Add click event listener to the contact link
contactLink.addEventListener('click', function(event) {
    // Prevent the default behavior of the link
    event.preventDefault();

    // Close login container if open
    loginContainer.style.display = 'none';

    // Close signup container if open
    signupContainer.style.display = 'none';

    // Toggle the display of the contact container
    if (contactContainer.style.display === 'block') {
        contactContainer.style.display = 'none';
    } else {
        contactContainer.style.display = 'block';
    }

    // Reset the message container
    document.getElementById('messageContainer').innerText = '';
});

// Add click event listener to the close button in the contact container
document.querySelector('#contactContainer input[value="Close"]').addEventListener('click', function(event) {
    // Prevent the default behavior of the button
    event.preventDefault();

    // Close the contact container
    contactContainer.style.display = 'none';
});

// Login
var loginLink = document.getElementById('loginLink');
var loginContainer = document.getElementById('loginContainer');
var signupButton = document.getElementById('signupButton');
var signupContainer = document.getElementById('signupContainer');

// Add click event listener to the login link
loginLink.addEventListener('click', function(event) {
    // Prevent the default behavior of the link
    event.preventDefault();

    // Close contact container if open
    contactContainer.style.display = 'none';

    // Toggle the display of the login container
    if (loginContainer.style.display === 'block') {
        loginContainer.style.display = 'none';
    } else {
        loginContainer.style.display = 'block';
    }

    // Hide the signup container
    signupContainer.style.display = 'none';
});

// Add click event listener to the signup button
signupButton.addEventListener('click', function(event) {
    // Prevent the default behavior of the button
    event.preventDefault();

    // Close contact container if open
    contactContainer.style.display = 'none';

    // Hide the login container
    loginContainer.style.display = 'none';

    // Toggle the display of the signup container
    signupContainer.style.display = signupContainer.style.display === 'none' ? 'block' : 'none';
});

// Add click event listener to the close button in the login container
document.querySelector('#loginContainer input[value="Close"]').addEventListener('click', function(event) {
    // Prevent the default behavior of the button
    event.preventDefault();

    // Close the login container
    loginContainer.style.display = 'none';
});

// Add click event listener to the close button in the signup container
document.querySelector('#signupContainer input[value="Close"]').addEventListener('click', function(event) {
    // Prevent the default behavior of the button
    event.preventDefault();

    // Close the signup container
    signupContainer.style.display = 'none';
});

