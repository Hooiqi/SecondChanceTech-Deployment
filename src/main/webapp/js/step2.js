const shippingOptions = [
            {
                id: 1,
                price: 17,
                method: "Regular shipment",
                date: "17 Jan, 2026",
                days: 20
            },
            {
                id: 2,
                price: 30,
                method: "Get your delivery as soon as possible",
                date: "5 Jan, 2026",
                days: 8
            }
        ];

        let selectedShippingIndex = 0;

        function renderShippingOptions() {
            const container = document.getElementById('shippingList');
            container.innerHTML = '';

            shippingOptions.forEach((option, index) => {
                const isSelected = index === selectedShippingIndex;
                const optionDiv = document.createElement('div');
                optionDiv.className = 'shipment-card';
                optionDiv.setAttribute('data-state', isSelected ? 'Selected' : 'Not Selected');
                optionDiv.onclick = () => selectShipping(index);

                // Calculate delivery date based on current date
                const deliveryDate = new Date();
                deliveryDate.setDate(deliveryDate.getDate() + option.days);
                const formattedDate = deliveryDate.toLocaleDateString('en-MY', {
                    day: 'numeric',
                    month: 'short',
                    year: 'numeric'
                });

                optionDiv.innerHTML = `
                    <div class="card-left">
                        <div class="radio-box">
                            <div class="radio-outer"></div>
                            <div class="radio-inner"></div>
                        </div>
                        <div class="price-text">RM ${option.price}</div>
                        <div class="method-text">${option.method}</div>
                    </div>
                    <div class="date-text">${formattedDate}</div>
                `;

                container.appendChild(optionDiv);
            });

            updateShippingInSession();
        }

        function selectShipping(index) {
            selectedShippingIndex = index;
            renderShippingOptions();
        }

        function updateShippingInSession() {
            const selectedOption = shippingOptions[selectedShippingIndex];

            // Update shipping fee in cart totals
            const subtotal = parseFloat(sessionStorage.getItem('subtotal')) || 0;
            const newShipping = selectedOption.price;
            const newTotal = subtotal + newShipping;

            sessionStorage.setItem('shipping', newShipping);
            sessionStorage.setItem('total', newTotal);
            sessionStorage.setItem('selectedShipping', JSON.stringify(selectedOption));

            // Calculate and store delivery date
            const deliveryDate = new Date();
            deliveryDate.setDate(deliveryDate.getDate() + selectedOption.days);
            sessionStorage.setItem('deliveryDate', deliveryDate.toLocaleDateString('en-MY', {
                day: 'numeric',
                month: 'short',
                year: 'numeric'
            }));
        }

        // Initialize on page load
        document.addEventListener('DOMContentLoaded', function() {
            // Check if there's a saved shipping selection
            const savedShipping = sessionStorage.getItem('selectedShipping');
            if (savedShipping) {
                try {
                    const saved = JSON.parse(savedShipping);
                    const index = shippingOptions.findIndex(opt => opt.id === saved.id);
                    if (index !== -1) {
                        selectedShippingIndex = index;
                    }
                } catch(e) {
                    console.error('Error loading shipping selection:', e);
                }
            }
            renderShippingOptions();
        });