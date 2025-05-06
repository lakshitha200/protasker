
// Apply theme on every page load
function initTheme() {
  
    const savedTheme = localStorage.getItem('theme') || 'light';
    document.documentElement.setAttribute('data-theme', savedTheme);
    updateIcon(savedTheme);
  }
  
  // Toggle function
  function toggleTheme(icon) {
    const newTheme = document.documentElement.getAttribute('data-theme') === 'dark' ? 'light' : 'dark';
    document.documentElement.setAttribute('data-theme', newTheme);
    localStorage.setItem('theme', newTheme);
    updateIcon(icon,newTheme);
  }

  function updateIcon(icon,theme) {
    icon.firstElementChild.className = theme === 'dark' ? 'fas fa-sun' : 'fas fa-moon';
  }
  
  // Initialize theme on page load
  initTheme();
  
  // Make toggleTheme() available globally
  window.toggleTheme = toggleTheme;

 


//   <!-- In your header/footer (shared across all pages) -->
// <button onclick="toggleTheme()">
//   <i id="themeIcon">🌓</i>
// </button>


    // const toggleBtn = document.getElementById('themeToggle');
    // const icon = toggleBtn.querySelector('i');
    
    // // Check saved preference (or default to light)
    // const currentTheme = localStorage.getItem('theme') || 'light';
    // document.documentElement.setAttribute('data-theme', currentTheme);
    // updateIcon(currentTheme);

    // // Toggle function
    // toggleBtn.addEventListener('click', () => {
    //   const newTheme = document.documentElement.getAttribute('data-theme') === 'dark' ? 'light' : 'dark';
    //   document.documentElement.setAttribute('data-theme', newTheme);
    //   localStorage.setItem('theme', newTheme);
    //   updateIcon(newTheme);
    // });

    // // Update icon
    // function updateIcon(theme) {
    //   icon.className = theme === 'dark' ? 'fas fa-sun' : 'fas fa-moon';
    // }
