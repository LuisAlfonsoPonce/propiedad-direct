(function () {
    "use strict";

    /**
     * Easy selector helper function
     */
    const select = (el, all = false) => {
        el = el.trim()
        if (all) {
            return [...document.querySelectorAll(el)]
        } else {
            return document.querySelector(el)
        }
    }

    /**
     * Easy event listener function
     */
    const on = (type, el, listener, all = false) => {
        let selectEl = select(el, all)
        if (selectEl) {
            if (all) {
                selectEl.forEach(e => e.addEventListener(type, listener))
            } else {
                selectEl.addEventListener(type, listener)
            }
        }
    }

    /**
     * Easy on scroll event listener
     */
    const onscroll = (el, listener) => {
        el.addEventListener('scroll', listener)
    }

    /**
     * Toggle .navbar-reduce
     */
    let selectHNavbar = select('.navbar-default')
    if (selectHNavbar) {
        onscroll(document, () => {
            if (window.scrollY > 100) {
                selectHNavbar.classList.add('navbar-reduce')
                selectHNavbar.classList.remove('navbar-trans')
            } else {
                selectHNavbar.classList.remove('navbar-reduce')
                selectHNavbar.classList.add('navbar-trans')
            }
        })
    }

    /**
     * Back to top button
     */
    let backtotop = select('.back-to-top')
    if (backtotop) {
        const toggleBacktotop = () => {
            if (window.scrollY > 100) {
                backtotop.classList.add('active')
            } else {
                backtotop.classList.remove('active')
            }
        }
        window.addEventListener('load', toggleBacktotop)
        onscroll(document, toggleBacktotop)
    }

    /**
     * Preloader
     */
    let preloader = select('#preloader');
    if (preloader) {
        window.addEventListener('load', () => {
            preloader.remove()
        });
    }

    /**
     * Search window open/close
     */
    let body = select('body');
    on('click', '.navbar-toggle-box', function (e) {
        e.preventDefault()
        body.classList.add('box-collapse-open')
        body.classList.remove('box-collapse-closed')
    })

    on('click', '.close-box-collapse', function (e) {
        e.preventDefault()
        body.classList.remove('box-collapse-open')
        body.classList.add('box-collapse-closed')
    })

    /**
     * Intro Carousel
     */
    new Swiper('.intro-carousel', {
        speed: 600,
        loop: true,
        autoplay: {
            delay: 2000,
            disableOnInteraction: false
        },
        slidesPerView: 'auto',
        pagination: {
            el: '.swiper-pagination',
            type: 'bullets',
            clickable: true
        }
    });

    /**
     * Property carousel
     */
    new Swiper('#property-carousel', {
        speed: 600,
        loop: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false
        },
        slidesPerView: 'auto',
        pagination: {
            el: '.propery-carousel-pagination',
            type: 'bullets',
            clickable: true
        },
        breakpoints: {
            320: {
                slidesPerView: 1,
                spaceBetween: 20
            },

            1200: {
                slidesPerView: 3,
                spaceBetween: 20
            }
        }
    });

    /**
     * News carousel
     */
    new Swiper('#news-carousel', {
        speed: 600,
        loop: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false
        },
        slidesPerView: 'auto',
        pagination: {
            el: '.news-carousel-pagination',
            type: 'bullets',
            clickable: true
        },
        breakpoints: {
            320: {
                slidesPerView: 1,
                spaceBetween: 20
            },

            1200: {
                slidesPerView: 3,
                spaceBetween: 20
            }
        }
    });

    new Swiper('#agents-carousel', {
        speed: 600,
        loop: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false
        },
        slidesPerView: 'auto',
        pagination: {
            el: '.agents-carousel-pagination',
            type: 'bullets',
            clickable: true
        },
        breakpoints: {
            320: {
                slidesPerView: 1,
                spaceBetween: 20
            },

            1200: {
                slidesPerView: 3,
                spaceBetween: 20
            }
        }
    });


    /**
     * Testimonial carousel
     */
    new Swiper('#testimonial-carousel', {
        speed: 600,
        loop: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false
        },
        slidesPerView: 'auto',
        pagination: {
            el: '.testimonial-carousel-pagination',
            type: 'bullets',
            clickable: true
        }
    });

    /**
     * Property Single carousel
     */
    new Swiper('#property-single-carousel', {
        speed: 600,
        loop: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false
        },
        pagination: {
            el: '.property-single-carousel-pagination',
            type: 'bullets',
            clickable: true
        }
    });

    /**
     * Function to preview image
     */
    const previewImage = (event) => {
        const reader = new FileReader();
        reader.onload = function () {
            const output = select('#outputImage');
            output.src = reader.result;
            output.style.display = 'block';

            // Ocultar la imagen Base64 si existe
            const imageBase64 = select('#imageBase64');
            if (imageBase64) {
                imageBase64.style.display = 'none';
            }
        };
        reader.readAsDataURL(event.target.files[0]);
    }

    // Add event listener to the file input
    const imageFileInput = select('#imageFile');
    if (imageFileInput) {
        imageFileInput.addEventListener('change', previewImage);
    }

	console.log('main.js loaded'); // Verificar que el archivo se carga

	window.addEventListener('load', function () {
	    console.log('Window load event fired'); // Verificar que el evento load se dispare

	    const clientAutocomplete = document.getElementById('clientAutocomplete');
	    const clientList = document.getElementById('clientList');
	    const clientId = document.getElementById('client');

	    if (!clientAutocomplete) {
	        console.error('clientAutocomplete element not found');
	        return;
	    }

	    clientAutocomplete.addEventListener('input', function () {
	        const searchTerm = this.value.toLowerCase(); // Convertir a minúsculas
	        console.log('Input event fired. Search term:', searchTerm); // Depuración

	        if (searchTerm.length < 2) {
	            clientList.innerHTML = '';
	            return;
	        }

	        fetch(`/autocomplete-clients?term=${searchTerm}`)
	            .then(response => {
	                if (!response.ok) {
	                    throw new Error('Network response was not ok');
	                }
	                return response.json();
	            })
	            .then(clients => {
	                console.log('Clients fetched:', clients); // Depuración
	                clientList.innerHTML = '';
	                clients.forEach(client => {
	                    const li = document.createElement('li');
	                    li.textContent = `${client.firstName} ${client.lastName}`;
	                    li.classList.add('list-group-item');
	                    li.addEventListener('click', function () {
	                        clientAutocomplete.value = `${client.firstName} ${client.lastName}`;
	                        clientId.value = client.clientId;
	                        clientList.innerHTML = '';
	                        console.log('Client selected:', client); // Depuración
	                    });
	                    clientList.appendChild(li);
	                });
	            })
	            .catch(error => {
	                console.error('Fetch error:', error); // Depuración
	            });
	    });
	});



})();
