getAllUsers()

function getAllUsers() {
    fetch("/admin/allUsers").then((response) => {
        response.json().then((users) => {
            users.forEach((user) => {
                addUserTable(user)
            });
        });
    });
}

function addUserTable(user) {
    $('#allUsersTable').append(
        '<tr>' +
        '<td>' + user.id + '</td>' +
        '<td>' + user.name + '</td>' +
        '<td>' + user.age + '</td>' +
        '<td>' + user.email + '</td>' +
        '<td>' + user.roles.map(roleUser => roleUser.name.replace("ROLE_", "")) + '</td>' +
        '<td><button onclick="modalEdit(' + user.id + ')" class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#modal">Edit</button></td>' +
        '<td><button onclick="modalDelete(' + user.id + ')" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#modalDel">Delete</button></td>' +
        '</tr>'
    )
}

function modalEdit(id) {
    fetch("/admin/" + id).then(resp => {
        resp.json().then(user => {
            $('#idEdit').val(user.id)
            $('#nameEdit').val(user.name)
            $('#ageEdit').val(user.age)
            $('#emailEdit').val(user.email)
        })
    })
}

function newUser() {
    let mass = JSON.stringify({
        name : document.getElementById('newName').value,
        age : document.getElementById('newAge').value,
        email : document.getElementById('newEmail').value,
        password : document.getElementById('newPassword').value,
        roles : Array.from(document.getElementById('newRole').selectedOptions)
            .map(opt => {
                if (opt.value === "ROLE_USER"){
                    return {"id": 2}
                }
                if (opt.value === "ROLE_ADMIN"){
                    return {"id": 1}
                }
            })
    })
    fetch("/admin", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'},
        body: mass
    }).then(() => {
        getAllUsers();
        document.getElementById("create").reset();
    })
}

function editUser() {
    let mass = JSON.stringify({
        id : document.getElementById('idEdit').value,
        name : document.getElementById('nameEdit').value,
        age : document.getElementById('ageEdit').value,
        email : document.getElementById('emailEdit').value,
        password : document.getElementById('passwordEdit').value,
        roles : Array.from(document.getElementById('selectRole').selectedOptions)
            .map(opt => {
                if (opt.value === "ROLE_USER"){
                    return {"id": 2}
                }
                if (opt.value === "ROLE_ADMIN"){
                    return {"id": 1}
                }
            })
    })
    return fetch('/admin', {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'},
        body: mass
    }).then(() => {
        getAllUsers()

    })
}

function modalDelete(id) {
    fetch("/admin/" + id).then(resp => {
        resp.json().then(user => {
            $('#deleteId').val(user.id)
            $('#deleteName').val(user.name)
            $('#deleteAge').val(user.age)
            $('#deleteEmail').val(user.email)
            user.roles.map(role => {
                $('deleteSelectRole').append('<options> id"' + role.id + '" name="' + role.role + '">' + role.role + '</options>')
            })

        })
    }).then(()=> {

    })
}

function deleteUser() {
    fetch("/admin/" + document.getElementById('deleteId').value, {method : 'DELETE'}).then(() => getAllUsers())
}