package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.OfertaPersistence;
import java.util.Calendar;
import java.util.Date;
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

    @Inject
    private PropuestaLogic propuestaLogic; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public OfertaEntity createOferta(OfertaEntity entity) throws BusinessLogicException {

        LOGGER.info("Inicia el proceso de creación de una oferta");
        // Invoca la persistencia para crear la oferta

        List<OfertaEntity> ofertas = persistenceOferta.findAll();
        for (int i = 0; i < ofertas.size(); i++) {
            if (ofertas.get(i).getCodigo().equals(entity.getCodigo())) {
                throw new BusinessLogicException("Ya existe una oferta con el código " + entity.getCodigo());
            }
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
    public List<OfertaEntity> getOfertas() throws BusinessLogicException {

        LOGGER.info("Inicia proceso de consultar todas las Ofertas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.

        List<OfertaEntity> listaOfertas = persistenceOferta.findAll();
        LOGGER.info("Termina proceso de consultar todas las Ofertas");

        if (listaOfertas.isEmpty()) {
            throw new BusinessLogicException("No hay ofertas existentes por mostrar.");
        }
        return listaOfertas;

    }

    /**
     * Obtiene los datos de una oferta a partir de su ID.
     *
     * @param id Identificador de la oferta a consultar.
     * @return una OfertaEntity con los datos de la oferta consultada.
     * @throws
     * co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException
     */
    public OfertaEntity getOferta(Long id) throws BusinessLogicException {

        LOGGER.info("Inicia proceso de consultar una Oferta según el id recibido por parámetro");
        OfertaEntity oferta = persistenceOferta.find(id);

        if (oferta == null) {
            throw new BusinessLogicException("No existen ofertas con ese id.");
        }
        LOGGER.info("Termina proceso de consultar una Oferta");

        return oferta;

    }

    /**
     * Actualiza la información de una Oferta.
     *
     * @param entity Oferta con los nuevos datos.
     * @param id id de la oferta
     * @return Instancia de OfertaEntity con los datos actualizados.
     * @throws BusinessLogicException si la oferta ya fue utilizada.
     *
     */
    public OfertaEntity updateOferta(Long id, OfertaEntity entity) throws BusinessLogicException {

        LOGGER.info("Inicia la modificación de una oferta");

        OfertaEntity oferta = persistenceOferta.find(id);

        if (oferta != null) {
            if (entity.getCodigo() != null) {
                entity.setCodigo(oferta.getCodigo());
            }
            if (entity.getDescripcion() == null) {
                entity.setDescripcion(persistenceOferta.find(id).getDescripcion());
            }
            if (entity.getEmpleado() == null) {
                entity.setEmpleado(persistenceOferta.find(id).getEmpleado());
            }
            if (entity.getName() == null) {
                entity.setName(persistenceOferta.find(id).getName());
            }
            if (entity.getCantidadActual() < entity.getCantidadInicial()) {
                throw new BusinessLogicException("No puede modificarse la oferta porque ya fue utilizada.");
            }
            Date date = Calendar.getInstance().getTime();

            if (date.after(entity.getFechaVigencia())) {
                throw new BusinessLogicException("No puede modificarse la oferta porque la fecha de vencimiento se cumplió.");
            }
        }

        LOGGER.info("Finaliza la modificación");
        return persistenceOferta.update(entity);

    }

    /**
     * No se implementó
     */
    /**
     * public PropuestaEntity addPropuesta(PropuestaEntity propuesta, Long
     * ofertaId) throws BusinessLogicException {
     * propuesta.setOferta(getOferta(ofertaId));
     * propuestaLogic.createPropuesta(propuesta);
     * getOferta(ofertaId).getPropuestas().add(propuesta); return propuesta;
     */
    /**
     * Elimina la oferta buscada por el id ingresado.
     *
     * @param id de la oferta a eliminar.
     * @throws BusinessLogicException en caso de que no encuentre la oferta por
     * el id.
     */
    public void deleteOferta(Long id) throws BusinessLogicException {

        if (persistenceOferta.find(id) == null) {

            throw new BusinessLogicException("No se encontró ninguna oferta con ese id.");
        }
        persistenceOferta.delete(id);

    }

    /**
     * Obtiene una lista de ofertas a partir de palabras clave.
     *
     * @param nombre Identificador de la oferta a consultar.
     * @return una List de OfertaEntity con los datos de las ofertas
     * encontradas.
     * @throws
     * co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException
     */
    public List<OfertaEntity> getOfertasNombre(String nombre) throws BusinessLogicException {

        if (persistenceOferta.findByName(nombre).isEmpty()) {
            throw new BusinessLogicException("no hay ofertas con ese nombre");
        }

        return persistenceOferta.findByName(nombre);

    }

    /**
     * Obtiene los datos de una oferta a partir de su código.
     *
     * @param codigoOferta Identificador de la oferta a consultar.
     * @return una OfertaEntity con los datos de la oferta consultada.
     * @throws
     * co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException
     */
    public OfertaEntity getOfertaPorCodigo(String codigoOferta) throws BusinessLogicException {

        OfertaEntity oferta = null;
        if (persistenceOferta.findByCodigo(codigoOferta) != null) {
            oferta = persistenceOferta.findByCodigo(codigoOferta);
        } else {
            throw new BusinessLogicException("no hay ofertas con ese código");
        }

        return oferta;
    }

}
