package com.kevinproject.backtienda.security.SecurityController;

import com.kevinproject.backtienda.model.mensaje;
import com.kevinproject.backtienda.model.producto;
import com.kevinproject.backtienda.repository.productoRep;
import com.kevinproject.backtienda.security.SecurityDto.JwtDto;
import com.kevinproject.backtienda.security.SecurityDto.LoginUsuario;
import com.kevinproject.backtienda.security.SecurityDto.NuevoUsuario;
import com.kevinproject.backtienda.security.SecurityEntity.Rol;
import com.kevinproject.backtienda.security.SecurityEntity.Usuario;
import com.kevinproject.backtienda.security.SecurityEnums.RolNombre;
import com.kevinproject.backtienda.security.SecurityJWT.JwtProvider;
import com.kevinproject.backtienda.security.SecurityService.RolServ;
import com.kevinproject.backtienda.security.SecurityService.UsuarioServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioServ usuarioServ;
    @Autowired
    RolServ rolServ;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    com.kevinproject.backtienda.repository.productoRep productoRep;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsario, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity("Datos Invalidos Reintente Por Favor", HttpStatus.BAD_REQUEST);
        }
        if (usuarioServ.existsByNombreUsuario(nuevoUsario.getNombreUsuario())) {
            return new ResponseEntity("Ese Nombre Ya Está En Uso", HttpStatus.BAD_REQUEST);
        }
        if (usuarioServ.existsByEmail(nuevoUsario.getEmail())) {
            return new ResponseEntity("Ese Email Ya Está En Uso", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario= new Usuario(nuevoUsario.getNombre(),nuevoUsario.getNombreUsuario(),nuevoUsario.getEmail(),passwordEncoder.encode(nuevoUsario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolServ.findByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsario.getRoles().contains("admin")){
            roles.add(rolServ.findByRolNombre(RolNombre.ROLE_ADMIN).get());}
        usuario.setRoles(roles);
        usuarioServ.save(usuario);
        return new ResponseEntity("Usuario Guardado",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("campos mal puestos", HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generarTkn(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @PutMapping("/addToCar/{nombreUsuario}")
    public ResponseEntity<?> addToCar(@Valid @RequestBody producto producto,BindingResult bindingResult,@PathVariable(name = "nombreUsuario")String nombreUsuario) {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity("Error en campos del producto",HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = usuarioServ.getByNombreUsuario(nombreUsuario).get();
       Set<producto> productos = usuario.getProductos();
       productos.add(producto);
       usuario.setProductos(productos);
       usuarioServ.save(usuario);
       return new ResponseEntity("Producto añadido",HttpStatus.OK);
    }

    @DeleteMapping("/deletefromcar/{nombreusuario}/{productoid}")
    public ResponseEntity<?> deleteFromCar(@PathVariable(name = "productoid")int productoid,@PathVariable(name = "nombreusuario")String nombreUsuario) {
        Usuario usuario = usuarioServ.getByNombreUsuario(nombreUsuario).get();
        Set<producto> productos = usuario.getProductos();
        producto producto = productoRep.findById(productoid).get();
        productos.remove(producto);
        usuario.setProductos(productos);
        usuarioServ.save(usuario);
        return new ResponseEntity("Producto Eliminado",HttpStatus.OK);
    }

    @GetMapping("/car/{nombreUsuario}")
    public Set<producto> getProductsInCar(@PathVariable(name = "nombreUsuario")String nombreUsuario){
        Usuario usuario = usuarioServ.getByNombreUsuario(nombreUsuario).get();
        return usuario.getProductos();
    }

}
