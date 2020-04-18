use VinoappDB;

-- 1. Mostrar el top-ten de los vinos mejor rankeados
select  vinos.nombre,avg(rankings.ranking) as promedio
from    rankings
join    vinos
on      vinos.vino_id=rankings.vino_id
where   rankings.vino_id in
    (select  distinct vino_id 
    from    rankings)
group by  vinos.nombre
order by  promedio desc
limit     10;
   
-- 2. ¿De qué cepas son los vinos de la cosecha 2019?
select    distinct cepas
from      vinos
where     cosecha=2019;
 /*   --prueba
select    cepas,cosecha
from      vinos;*/

-- 3. ¿Cuál es la cosecha Malbec con más premios?
select      vinos.cepas, 
            count(vinos.cosecha) as premios,vinos.`cosecha` 
from        premios
join        vinos
on          vinos.`vino_id`=premios.`vino_id` 
group by    vinos.cepas, vinos.cosecha
having      vinos.cepas like 'Malbec' 
order by    vinos.cepas;

-- 4. ¿Cuáles son las bodegas que no tienen vinos registrados?
select     bodegas.nombre_bodega
from       bodegas
left join  vinos
on         vinos.`bodega_id`=bodegas.`bodega_id`
where      vinos.`bodega_id` is null;  

-- 5. ¿Cuál es el usuario con más rankings?
select       max(rankings.usuario_id), count(rankings.usuario_id) num_rankings,usuarios.cuenta_usuario usuarios
from         rankings
join         usuarios
on           usuarios.`usuario_id`=rankings.`usuario_id`
group by     usuarios.cuenta_usuario, rankings.usuario_id
having       rankings.usuario_id=max(rankings.usuario_id)
order by     num_rankings desc 
limit        1;

select      usuario_id, ranking
from        rankings

select      u.cuenta_usuario, r.usuario_id, count(*) as num_rankings
from        rankings r
join        usuarios u
on          u.`usuario_id`=r.`usuario_id`
group by    u.cuenta_usuario,r.usuario_id
having      r.usuario_id=max(r.`usuario_id`);

 /*   --prueba
select rankings.usuario_id, usuarios.cuenta_usuario 
from   rankings
join   usuarios
on     rankings.`usuario_id`=usuarios.`usuario_id`;*/

-- 6. ¿De qué país y de qué región provienen los vinos con medallas de oro y/o puntuación de 100? 
select  bodegas.pais,vinos.terruno,premios.medalla,premios.puntaje
from    bodegas
join    vinos    on (vinos.bodega_id=bodegas.bodega_id)
join    premios on (vinos.vino_id=premios.vino_id)
where   (vinos.`terruno`,premios.medalla, premios.puntaje) = any 
    (select      vinos.terruno, premios.medalla, premios.puntaje
    from        vinos
    join        premios on (vinos.vino_id = premios.vino_id)
    where       medalla = 'oro' or puntaje = 100);

  /*  --prueba
select bodegas.pais,vinos.`terruno`,premios.`medalla`, premios.puntaje
from   premios
join   vinos on (premios.`vino_id`=vinos.`vino_id`)
join   bodegas on (vinos.`bodega_id`=bodegas.`bodega_id`);*/

-- 7. ¿Cuál es la más antigua de las bodegas que han obtenido premios y con cuáles de sus vinos?
    --Esta fue la query que se sugirió en clases, pero no se tomó en cuenta que se debía tener en cuenta 
    --que las bodegas debían contarse dentro de las premiadas y que, además, debía señalarse alguno de los vinos 
    --con los que ganó premios.
select * from bodegas
where    fundacion = 
    (select min(fundacion)
    from bodegas);

    --solución
select   bodegas.`fundacion` as antigüedad,bodegas.nombre_bodega,vinos.`nombre`
from     premios
join     vinos on vinos.`vino_id`=premios.`vino_id` 
join     bodegas  on bodegas.`bodega_id`=vinos.`bodega_id`
group by bodegas.fundacion, bodegas.nombre_bodega,vinos.nombre
having   bodegas.fundacion=
    (select  min(fundacion)
    from     bodegas
    join     vinos on vinos.bodega_id=bodegas.`bodega_id`
    join     premios on premios.`vino_id`=vinos.`vino_id`);
    
/*    --prueba
select   bodegas.`nombre_bodega` as bodegas, vinos.bodega_id, premios.vino_id, `vinos`.`nombre` as vinos, 
         bodegas.fundacion as antigüedad 
from     bodegas
join     vinos   on (vinos.bodega_id=bodegas.bodega_id)
join     premios on (vinos.vino_id=premios.vino_id)
group by bodegas.nombre_bodega,vinos.bodega_id,premios.vino_id 
order by min(bodegas.fundacion);*/

-- 8. ¿De qué país provienen la mayoría de usuarios de la aplicación?
select      pais, count(5) as usuarios 
from        usuarios
group by    pais
having      count(5) > 5;
 
  /*  --prueba
select pais,concat(nombre,' ',apellido) 
from usuarios
order by pais asc;*/

-- 9. ¿Cuáles vinos argentinos de la cosecha 2014 obtuvieron una puntuación superior a 90 en los distintos premios?
select       premios.vino_id, vinos.nombre as vino, vinos.cosecha as cosecha, premios.puntaje
from         premios 
join         vinos   on (vinos.vino_id=premios.vino_id)
join         bodegas on (vinos.bodega_id=bodegas.bodega_id)
where        vinos.cosecha = 2014 and premios.puntaje > 90 and bodegas.pais = 'Argentina'
group by     premios.vino_id,vinos.nombre,vinos.cosecha,premios.puntaje;

  /*  --prueba
select       vinos.nombre,vinos.cosecha,premios.puntaje, bodegas.pais 
from         premios
join         vinos   on (vinos.vino_id=premios.vino_id)
join         bodegas on (vinos.`bodega_id`=bodegas.`bodega_id`);*/

-- 10. ¿Cuáles son las tiendas que no vendieron ningún vino? 
select      tiendas.`nombre_T`,tiendas.`tienda_id`
from        tiendas
left join   rankings
on          rankings.tienda_id=tiendas.`tienda_id`
where       rankings.`tienda_id` is null;   
       


