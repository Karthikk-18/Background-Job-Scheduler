function goToEmailJob() {
    window.location.href = "email.html";
}

function goBack() {
    window.location.href = "index.html";
}

function submitEmailJob() {
    const to = document.getElementById('to').value;
    const subject = document.getElementById('subject').value;
    const body = document.getElementById('body').value;
    const statusEl = document.getElementById('status');

    // Validation
    if (!to || !subject || !body) {
        statusEl.textContent = '⚠️ Please fill in all fields';
        statusEl.className = 'error';
        return;
    }

    // Email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(to)) {
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

    // Show loading state
    statusEl.textContent = '⏳ Submitting job...';
    statusEl.className = '';

    fetch("http://localhost:8080/api/jobs", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
    .then(res => res.json())
    .then(data => {
        statusEl.textContent = '✅ Job submitted successfully!';
        statusEl.className = 'success';
        
        // Clear form after success
        setTimeout(() => {
            document.getElementById('to').value = '';
            document.getElementById('subject').value = '';
            document.getElementById('body').value = '';
        }, 2000);
    })
    .catch(err => {
        statusEl.textContent = '❌ Failed to submit job. Please try again.';
        statusEl.className = 'error';
    });
}