for $b in doc("Musicians.xml")/musicians/musician
  let $years := max($b/albums/album/@year) - min($b/albums/album/@year)+1
  let $count := count($b/albums/album)
  let $result := $years div $count
  order by $result
  return<musician>
    {$b/name}
    <rate>
      {$result}
    </rate>
  </musician>