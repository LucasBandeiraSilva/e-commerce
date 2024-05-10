export { autenticarUsuario, verificaUsuario, logOut };

const userToken = localStorage.getItem('user-token');
const authEndpoint = 'http://localhost:8080/auth/login';


function verificaUsuario() {
    if(userToken === null){
        window.location.href = '/login-usuario.html';
    }    
}

function logOut () {
    localStorage.removeItem('user-token');
}

async function autenticarUsuario(usuario){
        await fetch(authEndpoint, {
            method: 'POST',
            headers: {
                "Content-type": "application/json",
            },
            body: JSON.stringify(usuario)
        })
        .then(response => {
            if(response.ok){
                window.location.href = '/backoffice.html';
                return response.json();
            }
            console.log(response.status);
        })
        .then(data => {
            localStorage.setItem('user-token', data.token);
        })
        .catch(error => {
            console.log(error);
        });
}
