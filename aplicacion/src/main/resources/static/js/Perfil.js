document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('editar-perfil-modal');
    const btnEditar = document.querySelector('.editar-perfil');
    const btnCancelar = document.getElementById('cancelar-editar-perfil');
    const listaPreferencias = document.querySelector('.preferencias-lista');
    const modalEditarPreferencia = document.getElementById('editar-preferencia-modal');
    const btnCancelarEditarPreferencia = document.getElementById('cancelar-editar-preferencia');
    const formEditarPreferencia = modalEditarPreferencia.querySelector('form');
    const formEditarPerfil = document.getElementById('form-editar-perfil'); 

    // Funciones para mostrar y ocultar el modal de perfil
    function mostrarModal() {
        modal.style.display = 'flex';
    }

    function ocultarModal() {
        modal.style.display = 'none';
    }

    // Evento para abrir el modal de perfil
    btnEditar.addEventListener('click', function (e) {
        e.preventDefault();
        mostrarModal();
    });

    // Evento para cerrar el modal de perfil
    btnCancelar.addEventListener('click', ocultarModal);

    // Cerrar el modal si se hace clic fuera de él
    modal.addEventListener('click', function (e) {
        if (e.target === modal) {
            ocultarModal();
        }
    });

    // Funciones para mostrar y ocultar el modal de preferencias
    function mostrarModalPreferencia() {
        modalEditarPreferencia.style.display = 'flex';
    }

    function ocultarModalPreferencia() {
        modalEditarPreferencia.style.display = 'none';
    }

    // Evento para abrir el modal de preferencia
    document.querySelector('.boton-editar-preferencia').addEventListener('click', function (e) {
        e.preventDefault();
        mostrarModalPreferencia();
    });

    // Evento para cerrar el modal de preferencia
    btnCancelarEditarPreferencia.addEventListener('click', ocultarModalPreferencia);

    // Evento para enviar el formulario de agregar preferencia
    formEditarPreferencia.addEventListener('submit', function (e) {
        e.preventDefault();
        const preferencia = document.querySelector('#preferencia').value;

        // Enviar la preferencia al servidor
        agregarPreferenciaEnBaseDeDatos(preferencia);
        
        // Limpiar el formulario y cerrar el modal
        formEditarPreferencia.reset();
        ocultarModalPreferencia();
    });

    // Función para agregar preferencia en la base de datos
    function agregarPreferenciaEnBaseDeDatos(preferencia) {
        fetch(`/perfil/preferencias/guardar/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                'preferencia': preferencia
            }),
            credentials: 'same-origin'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al guardar la preferencia en el servidor.');
            }
            console.log('Preferencia guardada correctamente:', preferencia);

            // Crear el nuevo elemento de preferencia en la lista
            const li = document.createElement('li');
            li.classList.add('preferencia-item');
            const span = document.createElement('span');
            span.textContent = preferencia;

            const btnEliminar = document.createElement('button');
            btnEliminar.textContent = 'Eliminar';
            btnEliminar.classList.add('btn-eliminar-preferencia');
            btnEliminar.dataset.preferencia = preferencia;

            li.appendChild(span);
            li.appendChild(btnEliminar);
            listaPreferencias.appendChild(li);

            // Evento para eliminar la preferencia cuando se hace clic en el botón
            btnEliminar.addEventListener('click', function () {
                eliminarPreferencia(preferencia, li);
            });
        })
        .catch(error => {
            console.error('Error al agregar la preferencia:', error);
        });
    }

    // Función para eliminar preferencia de la base de datos y del DOM
    function eliminarPreferencia(preferencia, elementoDOM) {
        fetch(`/perfil/preferencia/eliminar`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                'preferencia': preferencia
            }),
            credentials: 'same-origin'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error al eliminar la preferencia: ${response.status}`);
            }
            console.log(`Preferencia "${preferencia}" eliminada correctamente.`);

            // Eliminar el elemento del DOM solo después de confirmar que fue eliminado en el servidor
            elementoDOM.remove();
        })
        .catch(error => {
            console.error('Error al eliminar la preferencia:', error);
        });
    }

    // Función para agregar eventos de eliminación a las preferencias ya existentes
    document.querySelectorAll('.btn-eliminar-preferencia').forEach(function (btnEliminar) {
        btnEliminar.addEventListener('click', function () {
            const li = btnEliminar.closest('li');
            const preferencia = btnEliminar.dataset.preferencia;
            eliminarPreferencia(preferencia, li);
        });
    });

    // Evento para enviar el formulario de editar perfil
    formEditarPerfil.addEventListener('submit', function (e) {
        e.preventDefault(); // Evitar el comportamiento por defecto

        const formData = new FormData(formEditarPerfil);

        fetch('/perfil/editar', {
            method: 'POST',
            body: formData,
            credentials: 'same-origin'
        })
        .then(response => {
            if (response.ok) {
                alert('Perfil actualizado correctamente');
                window.location.href = '/perfil'; // Redirigir a la página de perfil y recargarla
            } else {
                alert('Error al guardar el perfil');
            }
        })
        .catch(error => {
            console.error('Error al guardar el perfil:', error);
            alert('Hubo un error al guardar el perfil.');
        });
    });

    // Cerrar el modal de preferencia si se hace clic fuera de él
    modalEditarPreferencia.addEventListener('click', function (e) {
        if (e.target === modalEditarPreferencia) {
            ocultarModalPreferencia();
        }
    });
});
