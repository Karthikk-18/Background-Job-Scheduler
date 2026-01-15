// Check if user is already logged in
function checkAuth() {
    const token = localStorage.getItem('jwt_token');
    if (token) {
        // User is logged in, redirect to dashboard
        window.location.href = '/index.html';
    }
}

// Call on page load for login/register pages
if (window.location.pathname === '/login.html' || window.location.pathname === '/register.html') {
    checkAuth();
}

// Login function
async function login() {
    console.log("=== Login attempt ===");
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const statusEl = document.getElementById('status');

    // Validation
    if (!username || !password) {
        statusEl.textContent = '⚠️ Please fill in all fields';
        statusEl.className = 'error';
        return;
    }

    statusEl.textContent = '⏳ Logging in...';
    statusEl.className = '';

    try {
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        const data = await response.json();
        console.log("Login response:", data);

        if (response.ok) {
            localStorage.setItem('jwt_token', data.token);
            localStorage.setItem('username', data.username);

            statusEl.textContent = '✅ Login successful!';
            statusEl.className = 'success';

            setTimeout(() => {
                window.location.href = '/index.html';
            }, 1000);
        } else {
            statusEl.textContent = '❌ ' + (data.error || 'Login failed');
            statusEl.className = 'error';
        }
    } catch (error) {
        console.error("Login error:", error);
        statusEl.textContent = '❌ Login failed: ' + error.message;
        statusEl.className = 'error';
    }
}

async function register() {
    console.log("=== Registration attempt ===");
    
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const statusEl = document.getElementById('status');

    if (!username || !email || !password) {
        statusEl.textContent = '⚠️ Please fill in all fields';
        statusEl.className = 'error';
        return;
    }

    if (password.length < 6) {
        statusEl.textContent = '⚠️ Password must be at least 6 characters';
        statusEl.className = 'error';
        return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        statusEl.textContent = '⚠️ Please enter a valid email';
        statusEl.className = 'error';
        return;
    }

    statusEl.textContent = '⏳ Creating account...';
    statusEl.className = '';

    try {
        const response = await fetch('/api/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, email, password })
        });

        const data = await response.json();
        console.log("Register response:", data);

        if (response.ok) {
            localStorage.setItem('jwt_token', data.token);
            localStorage.setItem('username', data.username);

            statusEl.textContent = '✅ Account created successfully!';
            statusEl.className = 'success';

            setTimeout(() => {
                window.location.href = '/index.html';
            }, 1000);
        } else {
            statusEl.textContent = '❌ ' + (data.error || 'Registration failed');
            statusEl.className = 'error';
        }
    } catch (error) {
        console.error("Registration error:", error);
        statusEl.textContent = '❌ Registration failed: ' + error.message;
        statusEl.className = 'error';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const inputs = document.querySelectorAll('input');
    inputs.forEach(input => {
        input.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                if (window.location.pathname === '/login.html') {
                    login();
                } else if (window.location.pathname === '/register.html') {
                    register();
                }
            }
        });
    });
});