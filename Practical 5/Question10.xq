for $b in doc("Musicians.xml")/musicians/musician
  let $latest := max($b/albums/album/@year)
  order by -$latest
  return<musician>
    {$b/name}
    {$b/albums/album[@year=$latest]}
    <year>
      {$latest}
    </year>
  </musician>