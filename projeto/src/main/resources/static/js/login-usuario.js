import { autenticarUsuario, logOut } from './security/security.js';

const inputUsername = document.getElementById('login-user');
const inputPassword = document.getElementById('password-user');

addEventListener('DOMContentLoaded', () => {
    logOut();
});

document.getElementById('btn-logar').onclick = () => {
    const user = {
        login : inputUsername.value,
        senha : inputPassword.value
    }
    autenticarUsuario(user);
}

