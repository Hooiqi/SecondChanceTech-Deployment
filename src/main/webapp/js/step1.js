// Address data - will be loaded from session
let addresses = [];
let editingAddressId = null;

// Load user info from session and convert to address format
function loadUserInfoFromSession() {
    // This will be set by JSP from session attribute
    const currentUser = window.currentUserData;

    if (currentUser && currentUser.userId) {
        // Convert user data to address format
        const userAddress = {
            id: 1,
            name: `${currentUser.firstName} ${currentUser.lastName}`,
            address: `${currentUser.address}, ${currentUser.zipcode}, ${currentUser.city}, ${currentUser.state}`,
            phone: currentUser.phone,
            selected: true
        };

        addresses = [userAddress];
    } else {
        // Fallback to empty if no user in session
        addresses = [];
    }
}

// Render addresses
function renderAddresses() {
    const container = document.getElementById('addressList');

    if (addresses.length === 0) {
        container.innerHTML = '<p style="text-align: center; color: #999; padding: 40px;">No addresses added yet. Click "Add New Address" to get started.</p>';
        return;
    }

    let html = '';
    addresses.forEach(function(addr) {
        const selectedClass = addr.selected ? 'selected' : '';
        html += '<div class="address-card ' + selectedClass + '" onclick="selectAddress(' + addr.id + ')">';
        html += '<div class="address-card-info">';
        html += '<div class="address-header">';
        html += '<div class="custom-radio">';
        html += '<div class="radio-dot"></div>';
        html += '</div>';
        html += '<span class="user-name">' + addr.name + '</span>';
        html += '</div>';
        html += '<div class="address-details">';
        html += '<p class="address-line">' + addr.address + '</p>';
        html += '<p class="address-line">' + addr.phone + '</p>';
        html += '</div>';
        html += '</div>';
        html += '<div class="address-actions">';
        html += '<button class="icon-btn" onclick="event.stopPropagation(); editAddress(' + addr.id + ')">';
        html += '<img src="${pageContext.request.contextPath}/assets/Edit.png" class="icon-img" alt="Edit" onerror="this.src=\'data:image/svg+xml,%3Csvg xmlns=%22http://www.w3.org/2000/svg%22 width=%2224%22 height=%2224%22 viewBox=%220 0 24 24%22 fill=%22none%22 stroke=%22%23313647%22 stroke-width=%222%22%3E%3Cpath d=%22M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7%22/%3E%3Cpath d=%22M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z%22/%3E%3C/svg%3E\'">';
        html += '</button>';
        html += '<button class="icon-btn" onclick="event.stopPropagation(); deleteAddress(' + addr.id + ')">';
        html += '<img src="${pageContext.request.contextPath}/assets/Close.png" class="icon-img" alt="Delete" onerror="this.src=\'data:image/svg+xml,%3Csvg xmlns=%22http://www.w3.org/2000/svg%22 width=%2224%22 height=%2224%22 viewBox=%220 0 24 24%22%3E%3Cpath d=%22M18 6L6 18M6 6l12 12%22 stroke=%22%23313647%22 stroke-width=%222%22 stroke-linecap=%22round%22/%3E%3C/svg%3E\'">';
        html += '</button>';
        html += '</div>';
        html += '</div>';
    });

    container.innerHTML = html;
}

// Select address
function selectAddress(id) {
    addresses.forEach(function(addr) {
        addr.selected = (addr.id === id);
    });
    renderAddresses();
    saveToSession();
}

// Open modal for adding
function openAddressModal() {
    editingAddressId = null;
    document.getElementById('modalTitle').textContent = 'Add New Address';
    document.getElementById('saveBtn').textContent = 'Save Address';
    document.getElementById('addressForm').reset();
    document.getElementById('addressModal').style.display = 'block';
}

// Open modal for editing
function editAddress(id) {
    const addr = addresses.find(function(a) { return a.id === id; });
    if (!addr) return;

    editingAddressId = id;
    document.getElementById('modalTitle').textContent = 'Edit Address';
    document.getElementById('saveBtn').textContent = 'Save Changes';

    // Parse name (first + last)
    const nameParts = addr.name.split(' ');
    const firstName = nameParts[0] || '';
    const lastName = nameParts.slice(1).join(' ') || '';

    document.getElementById('fullName').value = addr.name;
    document.getElementById('phone').value = addr.phone;

    // Parse address - try to extract components
    const addressParts = addr.address.split(',').map(function(part) { return part.trim(); });

    if (addressParts.length >= 4) {
        document.getElementById('addressLine').value = addressParts[0] || '';
        document.getElementById('postalCode').value = addressParts[1] || '';
        document.getElementById('city').value = addressParts[2] || '';
        document.getElementById('state').value = addressParts[3] || '';
    } else {
        // Fallback if format is different
        document.getElementById('addressLine').value = addr.address;
        document.getElementById('postalCode').value = '';
        document.getElementById('city').value = '';
        document.getElementById('state').value = '';
    }

    document.getElementById('addressModal').style.display = 'block';
}

// Handle form submission
function handleFormSubmit(event) {
    event.preventDefault();

    const name = document.getElementById('fullName').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const addressLine = document.getElementById('addressLine').value.trim();
    const city = document.getElementById('city').value.trim();
    const state = document.getElementById('state').value.trim();
    const postalCode = document.getElementById('postalCode').value.trim();

    if (!name || !phone || !addressLine || !city || !state || !postalCode) {
        alert('Please fill in all required fields');
        return;
    }

    const fullAddress = addressLine + ', ' + postalCode + ', ' + city + ', ' + state;

    if (editingAddressId) {
        // Edit existing address - update in place
        const addr = addresses.find(function(a) { return a.id === editingAddressId; });
        if (addr) {
            addr.name = name;
            addr.phone = phone;
            addr.address = fullAddress;
        }
    } else {
        // Add new address - create new card
        const newId = addresses.length > 0 ? Math.max.apply(null, addresses.map(function(a) { return a.id; })) + 1 : 1;
        addresses.push({
            id: newId,
            name: name,
            address: fullAddress,
            phone: phone,
            selected: addresses.length === 0
        });
    }

    renderAddresses();
    saveToSession();
    closeAddressModal();
}

// Close modal
function closeAddressModal() {
    document.getElementById('addressModal').style.display = 'none';
    document.getElementById('addressForm').reset();
    editingAddressId = null;
}

function deleteAddress(id) {
    if (!confirm('Are you sure you want to delete this address?')) return;

    const wasSelected = addresses.find(function(a) { return a.id === id; }).selected;
    addresses = addresses.filter(function(a) { return a.id !== id; });

    // If deleted address was selected, select the first one
    if (wasSelected && addresses.length > 0) {
        addresses[0].selected = true;
    }

    renderAddresses();
    saveToSession();
}

// Go to next step
function goToNextStep() {
    const selectedAddress = addresses.find(function(a) { return a.selected; });
    if (!selectedAddress) {
        alert('Please select an address to continue');
        return;
    }

    // Save selected address to session
    sessionStorage.setItem('selectedAddress', JSON.stringify(selectedAddress));
    window.location.href = 'step2';
}

// Save to session storage
function saveToSession() {
    sessionStorage.setItem('addresses', JSON.stringify(addresses));
}

// Load from session storage
function loadFromSession() {
    const saved = sessionStorage.getItem('addresses');
    if (saved) {
        try {
            addresses = JSON.parse(saved);
        } catch (e) {
            console.error('Error loading addresses:', e);
        }
    }
}

// Close modal when clicking outside
window.onclick = function(event) {
    const modal = document.getElementById('addressModal');
    if (event.target === modal) {
        closeAddressModal();
    }
};

// Initialize on page load
document.addEventListener('DOMContentLoaded', function() {
    // First try to load from sessionStorage (if user already added addresses in this session)
    loadFromSession();

    // If no addresses in session, load from user data
    if (addresses.length === 0) {
        loadUserInfoFromSession();
    }

    renderAddresses();
});

// Save before unload
window.addEventListener('beforeunload', function() {
    saveToSession();
});