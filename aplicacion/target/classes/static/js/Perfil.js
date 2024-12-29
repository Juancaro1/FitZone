document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('editar-perfil-modal');
    const btnEditar = document.querySelector('.editar-perfil');
    const btnCancelar = document.getElementById('cancelar-editar-perfil');
    const listaPreferencias = document.querySelector('.preferencias-lista');
    const modalEditarPreferencia = document.getElementById('editar-preferencia-modal');
    const btnCancelarEditarPreferencia = document.getElementById('cancelar-editar-preferencia');
    const formEditarPreferencia = modalEditarPreferencia.querySelector('form');
    const formEditarPerfil = document.getElementById('form-editar-perfil'); 

    function mostrarModal() {
        modal.style.display = 'flex';
    }

    function ocultarModal() {
        modal.style.display = 'none';
    }

    btnEditar.addEventListener('click', function (e) {
        e.preventDefault();
        mostrarModal();
    });

    btnCancelar.addEventListener('click', ocultarModal);

    modal.addEventListener('click', function (e) {
        if (e.target === modal) {
            ocultarModal();
        }
    });

    function mostrarModalPreferencia() {
        modalEditarPreferencia.style.display = 'flex';
    }

    function ocultarModalPreferencia() {
        modalEditarPreferencia.style.display = 'none';
    }

    document.querySelector('.boton-editar-preferencia').addEventListener('click', function (e) {
        e.preventDefault();
        mostrarModalPreferencia();
    });

    btnCancelarEditarPreferencia.addEventListener('click', ocultarModalPreferencia);


    formEditarPreferencia.addEventListener('submit', function (e) {
        e.preventDefault();
        const preferencia = document.querySelector('#preferencia').value;

        agregarPreferenciaEnBaseDeDatos(preferencia);
        
        formEditarPreferencia.reset();
        ocultarModalPreferencia();
    });

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

            btnEliminar.addEventListener('click', function () {
                eliminarPreferencia(preferencia, li);
            });
        })
        .catch(error => {
            console.error('Error al agregar la preferencia:', error);
        });
    }

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
            elementoDOM.remove();
        })
        .catch(error => {
            console.error('Error al eliminar la preferencia:', error);
        });
    }

    document.querySelectorAll('.btn-eliminar-preferencia').forEach(function (btnEliminar) {
        btnEliminar.addEventListener('click', function () {
            const li = btnEliminar.closest('li');
            const preferencia = btnEliminar.dataset.preferencia;
            eliminarPreferencia(preferencia, li);
        });
    });

    formEditarPerfil.addEventListener('submit', function (e) {
        e.preventDefault(); 

        const formData = new FormData(formEditarPerfil);

        fetch('/perfil/editar', {
            method: 'POST',
            body: formData,
            credentials: 'same-origin'
        })
        .then(response => {
            if (response.ok) {
                alert('Perfil actualizado correctamente');
                window.location.href = '/perfil'; 
            } else {
                alert('Error al guardar el perfil');
            }
        })
        .catch(error => {
            console.error('Error al guardar el perfil:', error);
            alert('Hubo un error al guardar el perfil.');
        });
    });

    modalEditarPreferencia.addEventListener('click', function (e) {
        if (e.target === modalEditarPreferencia) {
            ocultarModalPreferencia();
        }
    });
});
