const API_URL = "http://localhost:8080/users";

// GET users
async function loadUsers() {
    const res = await fetch(API_URL);
    const users = await res.json();

    const list = document.getElementById("userList");
    list.innerHTML = "";

    users.forEach(user => {
        const li = document.createElement("li");
        li.textContent = user.username;
        list.appendChild(li);
    });
}

// POST user
async function addUser() {
    const nameInput = document.getElementById("nameInput");
    const emailInput = document.getElementById("emailInput");
    const passwordInput = document.getElementById("passwordInput");

    const name = nameInput.value;
    const email = emailInput.value;
    const password = passwordInput.value;

    const res = await fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: name,
            email: email,
            password: password
        })
    });
    console.log(res.status);

    // limpiar inputs
    nameInput.value = "";
    emailInput.value = "";
    passwordInput.value = "";

    if (!res.ok) {
        const errorData = await res.json();
        alert(errorData.error);
        return;
    }
    loadUsers();
}

// cargar al inicio
loadUsers();