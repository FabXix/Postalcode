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
        const button = document.createElement("button");
        button.textContent = "Delete user";
        button.addEventListener("click", async () => {
            try {
                const res = await fetch(API_URL + "/" + user.id, {
                    method: "DELETE",
                });
                if (res.ok) {
                    li.remove();
                } else {
                    const text = await res.text();
                    alert(text);
                }
            } catch (e) {
                alert(e.message);
            }
        })
        li.appendChild(button);
        list.appendChild(li);
    });
}

async function login() {
    const nameInput = document.getElementById("name");
    const passwordInput = document.getElementById("password");

    const name = nameInput.value;
    const password = passwordInput.value;

    try {
        console.log(API_URL + "/auth/login");
        const res = await fetch(API_URL + "/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: name,
                password: password
            })
        });

        const text = await res.text();

        if (res.ok) {
            alert("Login success: " + text);
        } else {
            alert("Error: " + text);
        }

    } catch (e) {
        alert("Network error: " + e.message);
    }
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