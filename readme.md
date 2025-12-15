# API – Productos y Pedidos

## Autenticación

La API cuenta con **registro y login**. Para poder acceder a los endpoints protegidos es necesario
estar autenticado.

### Endpoints de autenticación

- **POST** `/auth/register`  
  Registra un nuevo usuario.

- **POST** `/auth/login`  
  Inicia sesión y devuelve el token de autenticación (JWT).

> El token devuelto debe enviarse en los requests a los demás endpoints mediante el header:
>
> `Authorization: Bearer <token>`

---

### Login

```json
{
  "email": "usuario@gmail.com",
  "password": "Clave1234+"
}
```

### Registro

```json
{
  "nombre": "juan",
  "apellido": "doe",
  "email": "usuario@gmail.com",
  "password": "Clave1234+"
}
```

---

## Productos

### Endpoints

- **GET** `/productos`  
  Obtiene la lista de productos.

- **GET** `/productos/{id}`  
  Obtiene un producto por su ID.

- **POST** `/productos`  
  Crea un nuevo producto.

- **PUT** `/productos`  
  Actualiza un producto existente.

- **DELETE** `/productos/{id}`  
  Elimina un producto por su ID.

---

### DTO – Crear producto (`CreateProductoDto`)

```json
{
  "nombre": "Hamburguesas",
  "descripcion": "Hamburguesa congeladas",
  "categoria": "Comidas",
  "urlImg": "https://example.com/imagen.jpg",
  "precio": 2500.50,
  "stock": 10
}
```

**Validaciones:**

- `nombre`: obligatorio
- `descripcion`: entre 2 y 500 caracteres
- `categoria`: obligatoria
- `urlImg`: obligatoria
- `stock`: no puede ser negativo

---

### DTO – Actualizar producto (`UpdateProductoDto`)

```json
{
  "nombre": "Hamburguesa",
  "descripcion": "Hamburguesa congelada 360g",
  "categoria": "Comidas",
  "urlImg": "https://example.com/imagen2.jpg",
  "precio": 3200.00,
  "stock": 5
}
```

> Todos los campos son opcionales y solo se actualizarán los enviados.

---

## Pedidos

### Endpoints

- **GET** `/pedidos`  
  Obtiene la lista de pedidos.

- **GET** `/pedidos/{id}`  
  Obtiene un pedido por su ID.

- **POST** `/pedidos`  
  Crea un nuevo pedido.

- **PUT** `/pedidos`  
  Actualiza un pedido existente.

- **DELETE** `/pedidos/{id}`  
  Elimina un pedido por su ID.

---

### DTO – Crear pedido (`CreatePedidoDto`)

```json
{
  "nombreCliente": "Juan Pérez",
  "lineasPedido": [
    {
      "productoId": 1,
      "cantidad": 2
    },
    {
      "productoId": 3,
      "cantidad": 1
    }
  ]
}
```

---

### DTO – Actualizar pedido (`UpdatePedidoDto`)

```json
{
  "nombreCliente": "Juan Pérez",
  "fecha": "2025-12-15T20:30:00",
  "estado": "CONFIRMADO",
  "lineasPedido": [
    {
      "productoId": 1,
      "cantidad": 3
    }
  ]
}
```

---

### DTO – Línea de pedido (`CreateLineaPedidoDto`)

```json
{
  "productoId": 1,
  "cantidad": 2
}
```

---

## Notas

- Todos los endpoints (excepto login y register) requieren autenticación.
- Las fechas se envían en formato **ISO-8601**.
- Las validaciones se aplican automáticamente según los DTO definidos en el backend.

