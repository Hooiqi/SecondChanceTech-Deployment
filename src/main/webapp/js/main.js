
function openTab(evt, tabName) {
    var i, content, buttons;

    content = document.getElementsByClassName("tab-content");
    for (i = 0; i < content.length; i++) {
        content[i].style.display = "none";
        content[i].classList.remove("active");
    }

    buttons = document.getElementsByClassName("tab-btn");
    for (i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove("active");
    }

    const activeTab = document.getElementById(tabName);
    if (activeTab) {
        activeTab.style.display = "grid"; 
        activeTab.classList.add("active");
    }
    
    evt.currentTarget.classList.add("active");
}

function toggleFilter(element) {
    const group = element.parentElement;
    group.classList.toggle('collapsed');
}

function initReveal() {
    const observerOptions = {
        threshold: 0.15 
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('active');
            } else {
                entry.target.classList.remove('active');
            }
        });
    }, observerOptions);

    const elementsToReveal = document.querySelectorAll('.reveal');
    elementsToReveal.forEach(el => observer.observe(el));
}

window.addEventListener('load', () => {
    setTimeout(initReveal, 300); 
});