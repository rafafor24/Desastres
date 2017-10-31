package co.edu.uniandes.csw.traducciones.ejb;


import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.OfertaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author av.perezb
 */
@Stateless
public class OfertaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(OfertaLogic.class.getName());
    
    @Inject
    private OfertaPersistence persistenceOferta; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public OfertaEntity createOferta (OfertaEntity entity) throws BusinessLogicException {
        
        LOGGER.info("Inicia el proceso de creación de una oferta");
        // Invoca la persistencia para crear la oferta
        
        if (persistenceOferta.find(entity.getId())!= null)
        {
            throw new BusinessLogicException("Ya existe una oferta con ese id");
        }
        if (persistenceOferta.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una oferta con el nombre \"" + entity.getName() + "\"");
        }
        persistenceOferta.create(entity);
        LOGGER.info("Termina proceso de creación de una oferta");
        return entity;
        
    }
    
    /**
     * 
     * Obtener todas las Ofertas existentes en la base de datos.
     *
     * @return una lista de Ofertas.
     */
    public List<OfertaEntity> getOfertas() throws Exception {
        
        LOGGER.info("Inicia proceso de consultar todas las Ofertas");
         // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
         
        List<OfertaEntity> listaOfertas = persistenceOferta.findAll();
        LOGGER.info("Termina proceso de consultar todas las Ofertas");
        
        if (listaOfertas.size()== 0)
        {
                        
             throw new Exception("No hay ofertas existentes por mostrar.");
           
        }
        
        return listaOfertas;
                
    }
    
    /**
     * Obtiene los datos de una oferta a partir de su ID.
     * @param id Identificador de la oferta a consultar.
     * @return una OfertaEntity con los datos de la oferta consultada.
     */
    public OfertaEntity getOferta (Long id) throws BusinessLogicException {
        
        LOGGER.info("Inicia proceso de consultar una Oferta según el id recibido por parámetro");
        OfertaEntity oferta = persistenceOferta.find(id);
        
        if(oferta == null)
        {
            throw new BusinessLogicException("No existen ofertas con ese id.");
        }
        LOGGER.info("Termina proceso de consultar una Oferta");
        
        return oferta;      
        
    }
    
    /**
     * Actualiza la información de una Oferta.
     *
     * @param entity Oferta con los nuevos datos.
     * @return Instancia de OfertaEntity con los datos actualizados.
     * @throws BusinessLogicException si la oferta ya fue utilizada.
     **/
    public OfertaEntity updateOferta (OfertaEntity entity) throws BusinessLogicException {
        
        LOGGER.info("Inicia la modificación de una oferta");
        
        if( entity.getCantidadActual()< entity.getCantidadInicial()) {
            
            throw new BusinessLogicException("No puede modificarse la oferta porque ya fue utilizada.");
      }
        
        if (persistenceOferta.find(entity.getId())== null)
        {
            throw new BusinessLogicException("No existe una oferta con ese id.");
        }
         
        LOGGER.info("Finaliza la modificación");
        return persistenceOferta.update(entity);
        
    }
    
    public void deleteOferta (Long id) throws BusinessLogicException{
        
        if (persistenceOferta.find(id) == null) {
            
            throw new BusinessLogicException("No se encontró ninguna oferta con ese id.");
        }
        persistenceOferta.delete(id);      
       
    }

    public List<OfertaEntity> getOfertasNombre(String nombre) throws BusinessLogicException {
        
        if (persistenceOferta.findByName(nombre).isEmpty())
        {
            throw new BusinessLogicException("no hay ofertas con ese nombre");
        }
           
           return persistenceOferta.findByName(nombre);
   
    }
  
}
