function checkAuth() {
    const token = localStorage.getItem('jwt_token');
    if (!token) {
        window.location.href = '/login.html';
        return false;
    }
    return true;
}

function getToken() {
    return localStorage.getItem('jwt_token');
}

function logout() {
    localStorage.removeItem('jwt_token');
    localStorage.removeItem('username');
    window.location.href = '/login.html';
}

if (window.location.pathname === '/index.html' || window.location.pathname === '/email-job.html') {
    if (!checkAuth()) {
       
    } else {
        const username = localStorage.getItem('username');
        const userSpan = document.getElementById('username-display');
        if (userSpan) {
            userSpan.textContent = username || 'User';
        }
    }
}

function goToEmailJob() {
    window.location.href = "/email-job.html";
}

function goBack() {
    window.location.href = "/index.html";
}

function submitEmailJob() {
    console.log("=== Submit button clicked ===");
    
    const to = document.getElementById('to').value;
    const subject = document.getElementById('subject').value;
    const body = document.getElementById('body').value;
    const statusEl = document.getElementById('status');

    console.log("Form values:", { to, subject, body });

    if (!to || !subject || !body) {
        console.log("Validation failed: Missing fields");
        statusEl.textContent = '⚠️ Please fill in all fields';
        statusEl.className = 'error';
        return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(to)) {
        console.log("Validation failed: Invalid email");
        statusEl.textContent = '⚠️ Please enter a valid email address';
        statusEl.className = 'error';
        return;
    }

    const payload = {
        type: "EMAIL",
        payload: JSON.stringify({
            to: to,
            subject: subject,
            body: body
        })
    };

    console.log("=== Sending request ===");
    console.log("URL: /jobs");
    console.log("Method: POST");
    console.log("Payload:", JSON.stringify(payload, null, 2));

    statusEl.textContent = '⏳ Submitting job...';
    statusEl.className = '';

    const token = getToken();
    console.log("Using JWT token:", token ? "Token present" : "No token");

    fetch("/jobs", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token  
        },
        body: JSON.stringify(payload)
    })
    .then(response => {
        console.log("=== Response received ===");
        console.log("Status:", response.status);
        console.log("Status Text:", response.statusText);
        
        if (response.status === 401 || response.status === 403) {
            console.error("Authentication failed - redirecting to login");
            localStorage.removeItem('jwt_token');
            localStorage.removeItem('username');
            window.location.href = '/login.html';
            throw new Error('Session expired. Please login again.');
        }
        
        if (!response.ok) {
            return response.text().then(text => {
                console.error("Error response body:", text);
                throw new Error(`HTTP ${response.status}: ${text}`);
            });
        }
        
        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            return response.json();
        } else {
            return response.text();
        }
    })
    .then(data => {
        console.log("=== Success ===");
        console.log("Response data:", data);
        
        statusEl.textContent = '✅ Job submitted successfully!';
        statusEl.className = 'success';
        
        setTimeout(() => {
            document.getElementById('to').value = '';
            document.getElementById('subject').value = '';
            document.getElementById('body').value = '';
            statusEl.textContent = '';
            statusEl.className = '';
        }, 3000);
    })
    .catch(err => {
        console.error("=== Error ===");
        console.error("Error:", err);
        
        statusEl.textContent = '❌ Failed: ' + err.message;
        statusEl.className = 'error';
    });
}