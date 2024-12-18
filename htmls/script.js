document.addEventListener('DOMContentLoaded', () => {
    // Elementos del DOM
    const modal = document.getElementById('editar-perfil-modal');
    const editarPerfilBtn = document.querySelector('.editar-perfil'); // Botón para abrir el modal
    const cancelarBtn = document.getElementById('cancelar-editar-perfil'); // Botón para cerrar el modal
    const formEditarPerfil = document.getElementById('form-editar-perfil'); // Formulario de edición

    // Mostrar el modal al hacer clic en "Editar Perfil"
    editarPerfilBtn.addEventListener('click', (e) => {
        e.preventDefault(); // Evita que el enlace recargue la página
        modal.style.display = 'flex'; // Cambia a "flex" para mostrar el modal
    });

    // Ocultar el modal al hacer clic en "Cancelar"
    cancelarBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    // Opcional: Ocultar el modal al hacer clic fuera del contenido
    window.addEventListener('click', (e) => {
        if (e.target === modal) {
            modal.style.display = 'none';
        }
    });

    // Procesar el envío del formulario
    formEditarPerfil.addEventListener('submit', (e) => {
        e.preventDefault(); // Evita que el formulario se envíe de manera tradicional

        // Obtener los valores ingresados
        const nombre = document.getElementById('nombre').value.trim();
        const apellido = document.getElementById('apellido').value.trim();
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value.trim();

        // Validar los datos (opcional, puedes agregar más validaciones)
        if (!nombre || !apellido || !email || !password) {
            alert('Por favor, completa todos los campos.');
            return;
        }

        // Simulación de guardado (puedes integrar con una API o backend aquí)
        console.log('Datos guardados:', { nombre, apellido, email, password });
        alert('Perfil actualizado correctamente.');

        // Ocultar el modal
        modal.style.display = 'none';

        // Opcional: Actualizar los datos mostrados en la página principal
        // Aquí podrías actualizar el DOM con los datos nuevos si es necesario
    });
});
