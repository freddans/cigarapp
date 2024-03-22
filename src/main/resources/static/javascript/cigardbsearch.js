function searchCigars() {
    var keyword = document.getElementById("searchInput").value;
    if (keyword.trim() === "") {
        // If search input is empty, display all cigars
        displayAllCigars();
    } else {
        // If search input is not empty, filter cigars by name or brand
        filterCigarsByNameOrBrand(keyword);
    }
}

function filterCigarsByNameOrBrand(keyword) {
    const cigars = document.querySelectorAll("#cigarTableBody tr");
    cigars.forEach(function (cigar) {
        const name = cigar.querySelector("td:nth-child(2)").textContent.toLowerCase();
        const brand = cigar.querySelector("td:nth-child(3)").textContent.toLowerCase();
        console.log("Name: " + name + ", Brand: " + brand);
        if (name.includes(keyword.toLowerCase()) || brand.includes(keyword.toLowerCase())) {
            cigar.style.display = "table-row";
        } else {
            cigar.style.display = "none";
        }
    });
}

function displayAllCigars() {
    var cigars = document.querySelectorAll("#cigarTableBody tr");
    cigars.forEach(function (cigar) {
        cigar.style.display = "table-row";
    });
}


// Sorting function
// Add JavaScript function for sorting
function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.querySelector("table");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until no switching has been done: */
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.rows;
        /* Loop through all table rows (except the first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare, one from current row and one from the next: */
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /* Check if the two rows should switch place, based on the direction, ascending or descending: */
            if (dir === "asc") {
                if (n === 4 || n === 5 || n === 6) {
                    var numX = parseFloat(x.innerHTML.replace(".", ""));
                    var numY = parseFloat(y.innerHTML.replace(".", ""));
                    if (numX > numY) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                } else {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                }
            } else if (dir === "desc") {
                if (n === 4 || n === 5 || n === 6) {
                    var numX = parseFloat(x.innerHTML.replace(".", ""));
                    var numY = parseFloat(y.innerHTML.replace(".", ""));
                    if (numX < numY) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                } else {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                }
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /* If no switching has been done AND the direction is "asc", set the direction to "desc" and run the while loop again: */
            if (switchcount === 0 && dir === "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}