for $b in doc("Musicians.xml")/musicians/musician
  where $b/genre contains text "Pop"
  return<musicians>
  {$b}
  </musicians>