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

// Get the contact container
var contactContainer = document.getElementById('contactContainer');

// Add click event listener to the contact link
contactLink.addEventListener('click', function(event) {
    // Prevent the default behavior of the link
    event.preventDefault();

    // Get the computed style of the contact container
    var contactContainerStyle = window.getComputedStyle(contactContainer);

    // Check if the container is currently visible
    var isVisible = contactContainerStyle.display !== 'none';

    // Toggle the display of the contact container
    contactContainer.style.display = isVisible ? 'none' : 'block';

    // Reset the message container
    document.getElementById('messageContainer').innerText = '';
});

// Add submit event listener to the contact form
document.getElementById('contactForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form submission

    // Get form data
    var name = document.getElementById('name').value;
    var email = document.getElementById('email').value;
    var message = document.getElementById('message').value;

    // Construct mailto link
    var mailtoLink = 'mailto:flundell89@gmail.com' +
        '?subject=' + encodeURIComponent('Message from ' + name) +
        '&body=' + encodeURIComponent('From: ' + email + '\n\n' + message);

    // Open default email client with mailto link
    window.open(mailtoLink, '_blank');

    // Display "Message sent" message (optional)
    document.getElementById('messageContainer').innerText = 'Message sent';
});

/* login */
// <!-- Login container -->
// var loginLink = document.getElementById('loginLink');
//
// // Get the contact container
// var loginContainer = document.getElementById('loginContainer');
//
// // Add click event listener to the contact link
// loginLink.addEventListener('click', function(event) {
//     // Prevent the default behavior of the link
//     event.preventDefault();
//
//     // Get the computed style of the contact container
//     var contactContainerStyle = window.getComputedStyle(loginContainer);
//
//     // Check if the container is currently visible
//     var isVisible = contactContainerStyle.display !== 'none';
//
//     // Toggle the display of the contact container
//     loginContainer.style.display = isVisible ? 'none' : 'block';
//
//     // Reset the message container
//     document.getElementById('messageContainer').innerText = '';
// });

var loginLink = document.getElementById('loginLink');
var signupButton = document.getElementById('signupButton');
var loginContainer = document.getElementById('loginContainer');
var signupContainer = document.getElementById('signupContainer');

// Add click event listener to the login link
loginLink.addEventListener('click', function(event) {
    // Prevent the default behavior of the link
    event.preventDefault();

    // Check if the login container is already visible
    var loginContainerVisible = window.getComputedStyle(loginContainer).display !== 'none';

    // If the login container is not visible, show it
    if (!loginContainerVisible) {
        loginContainer.style.display = 'block';
    }

    // Hide the signup container
    signupContainer.style.display = 'none';
});

// Add click event listener to the signup button
signupButton.addEventListener('click', function(event) {
    // Prevent the default behavior of the button
    event.preventDefault();

    // Hide the login container
    loginContainer.style.display = 'none';

    // Toggle the display of the signup container
    signupContainer.style.display = signupContainer.style.display === 'none' ? 'block' : 'none';
});


